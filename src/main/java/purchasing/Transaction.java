package purchasing;

import models.Company;
import models.Depot;
import models.Product;
import models.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for carrying out all necessary transactions automatically.
 * <p>
 * For that to happen. The usage of the following method is necessary:
 *
 * @see Transaction#makeTransactions(Company, Company, Company)
 * <p>
 * This class is a Singleton. Therefore not meant to be instantiated multiple times
 * as it could possibly cause serious problems for any company involved.
 */
public class Transaction {

    // Instance of this class for the Singleton pattern
    private static Transaction instance = null;
    // List of sellers to be looped through as a buyer
    public ArrayList<Company> sellers = new ArrayList<>();
    // Instance of the Originator and Carer as part of the Memento design pattern
    public TicketOriginator originator = new TicketOriginator();
    public static TicketCarer ticketCarer = new TicketCarer();
    //
    public int currentBuyerId = -1;
    public int currentSellerId = -1;

    // Mark's requirements. Will greatly restrict the number of transactions a depot will perform.
    public int minCashAllowance = 50;
    public int maxCashAllowance = 100;

    private Transaction() {
    }

    /**
     * Performs all transactions/operations as required for the assignment.
     *
     * @param firstSeller
     * @param secondSeller
     * @param buyer
     */
    public void makeTransactions(Company buyer, Company firstSeller, Company secondSeller) {

        currentSellerId = 0;
        currentBuyerId = 0;
        setSellers(firstSeller, secondSeller);
        List<Company> sellers = getSellers();
        // Shuffling the sellers. This way, the output is much less predictable.
        Collections.shuffle(sellers);

        // Looping through buyer's depots
        buyer.getDepots().forEach(buyerDepot -> {

            currentBuyerId++;

            // Looping through seller companies
            for (int j = 0; j < sellers.size(); j++) {

                Company seller = sellers.get(j);
                boolean notReadyToBuy = false;
                boolean hasBought = false;

                if (notReadyToBuy || hasBought) {
                    break;
                }

                // Looping through sellers's depots
                for (int i = getCurrentSellerId(); i < seller.getDepots().size(); i++) {

                    Depot sellerDepot = seller.getDepots().get(i);

                    if (isReadyToBuy(buyerDepot, sellerDepot)) {

                        int buyingGoal = setBuyingGoal(buyerDepot, sellerDepot);

                        if (isReadyToSell(sellerDepot)) {
                            int quantityToBuy = quantityToBuy(sellerDepot, buyingGoal);
                            buy(quantityToBuy, buyerDepot, sellerDepot);

                            currentSellerId++;
                            hasBought = true;
                            break;
                        }
                    } else {
                        notReadyToBuy = true;
                        break;
                    }
                }

            }
        });
    }

    /**
     * Performs a purchase between two depots.
     * All necessary considerations are accounted for.
     *
     * @param quantity
     * @param seller
     * @param buyer
     */
    public void buy(int quantity, Depot buyer, Depot seller) {

        int productCost = seller.getStockList().get(0).getPrice();
        int totalCost = (quantity * productCost) + seller.getDelivery();

        pay(totalCost, buyer, seller);
        ship(quantity, buyer, seller);
        generateTicket(quantity, buyer, seller);

    }

    /**
     * Performs payment between two depots.
     *
     * @param totalCost
     * @param seller
     * @param buyer
     */
    private void pay(int totalCost, Depot buyer, Depot seller) {

        buyer.setCashAllowance(buyer.getCashAllowance() - totalCost);
        seller.setCashAllowance(seller.getCashAllowance() + totalCost);

    }

    /**
     * Ships products bought from Seller's depot to Buyer's.
     *
     * @param quantity
     * @param seller
     * @param buyer
     */
    private void ship(int quantity, Depot buyer, Depot seller) {

        for (int i = 0; i < quantity; i++) {

            Product temp = seller.getStockList().remove(0);
            buyer.getStorageList().add(temp);
        }
    }

    /**
     * Generates and stores a ticket for the performed transaction.
     *
     * @param quantity
     * @param seller
     * @param buyer
     */
    public Ticket generateTicket(int quantity, Depot buyer, Depot seller) {

        int productCost = seller.getStockList().get(0).getPrice();

        getOriginator().setBuyer(buyer.getOwner());
        getOriginator().setSeller(seller.getOwner());
        getOriginator().setBuyerDepotId(getCurrentBuyerId());
        getOriginator().setSellerDepotId(getCurrentSellerId());
        getOriginator().setDelivery(seller.getDelivery());
        getOriginator().setProductCost(productCost);
        getOriginator().setQuantity(quantity);
        return getTicketCarer().addTicket(getOriginator().saveTicketState());
    }

    /**
     * Decides the quantity of products to be sold, ensuring it does not surpass
     * the seller's cash maximum allowance.
     *
     * @param productQty      desired quantity of products to be bought.
     * @param productCost     cost of each individual product.
     * @param incomeThreshold amount of income that can't be surpassed.
     * @return settled quantity of products available to sell.
     */
    public int accountForMaxCashAllowance(int productQty, int productCost, int incomeThreshold) {

        int availability;

        boolean doesNotReachMaxCashAllowance =
                (productQty * productCost) < incomeThreshold;

        if (doesNotReachMaxCashAllowance) {
            availability = productQty;
        } else {
            availability = incomeThreshold / productCost;
        }

        return availability;
    }

    /**
     * Determines how many products a depot is capable of buying.
     * Ensuring the depot will not be left with its stock capacity below the required minimum amount.
     *
     * @param buyer
     * @param seller
     * @return The quantity of items this depot is capable of buying.
     */
    public int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.getDelivery();
        int productCost = seller.getStockList().get(0).getPrice();
        int stock = buyer.getStockList().size();
        int minimum = buyer.getStockMin();
        int purchasingPower = buyer.getCashAllowance() - getMinCashAllowance();
        int buyingGoal = (int) Math.floor((purchasingPower - deliveryCost) / productCost);
        int spaceAvailable = stock - minimum;

        if (buyingGoal > spaceAvailable) {
            return spaceAvailable;
        }

        return buyingGoal;
    }

    /**
     * Determines how many products will be bought from the chosen seller's depot.
     * Ensuring the seller will not be left with its stock capacity below the required minimum amount.
     * Or with more money than its maximum cash allowance.
     *
     * @param buyerDemand how many products the buyer wishes to buy.
     * @param seller      the selling depot.
     * @return Quantity of products to be bought.
     */
    public int quantityToBuy(Depot seller, int buyerDemand) {

        int stock = seller.getStockList().size();
        int minimum = seller.getStockMin();
        int productsInStock = stock - minimum;
        int productCost = seller.getStockList().get(0).getPrice();
        int deliveryCost = seller.getDelivery();
        int allowedIncome = (getMaxCashAllowance() - seller.getCashAllowance()) - deliveryCost;

        /*
        When the quantity of products available to sell is less than the buyer's demand,
        attempt to sell all available products, taking the maximum cash allowance into consideration.
         */
        if (productsInStock < buyerDemand) {
            return accountForMaxCashAllowance(productsInStock, productCost, allowedIncome);
        }

        /*
        When the quantity of products available to sell meets the buyer's demand,
        attempt to sell that amount, taking the maximum cash allowance into consideration.
         */
        return accountForMaxCashAllowance(buyerDemand, productCost, allowedIncome);

    }

    /**
     * Determines if the current buyer's depot is capable of buying at least one product.
     *
     * @param seller
     * @param buyer
     * @Return A true or false.
     */
    public Boolean isReadyToBuy(Depot buyer, Depot seller) {

        int cash = buyer.getCashAllowance();
        int currentAmountOfProductsBought = buyer.getStorageList().size();
        int maxStorageSpace = buyer.getStorageMax();
        int productPrice = seller.getStockList().get(0).getPrice();
        int deliveryCost = seller.getDelivery();
        int costOfPurchase = productPrice + deliveryCost;
        int purchasingPower = cash - getMinCashAllowance();

        /*
        1: Buying depot's cash has to be bigger than 50.
        2: Its storage space is not full.
        3: Has enough money to buy at least one product from the seller and pay for delivery.
         */
        return cash > getMinCashAllowance() &&
                currentAmountOfProductsBought < maxStorageSpace &&
                purchasingPower >= costOfPurchase;
    }

    /**
     * Determines if the current seller's depot is capable of selling at least one product.
     *
     * @param seller
     * @Return A true or false.
     */
    public Boolean isReadyToSell(Depot seller) {

        int cash = seller.getCashAllowance();
        int stockSpace = seller.getStockList().size();
        int minStockSpace = seller.getStockMin();
        int productCost = seller.getStockList().get(0).getPrice();
        int minimumCharge = productCost + seller.getDelivery();
        int newBalance = cash + minimumCharge;

        /*
        If selling one single item already leaves this seller with too much money.
        It won't be able to sell anything to anyone.
         */
        if (newBalance > getMaxCashAllowance()) {
            return false;
        }

        // Also, it can only sell products if its stock capacity is above the minimum required.
        return stockSpace > minStockSpace;
    }

    public List<Company> getSellers() {
        return sellers;
    }

    public TicketOriginator getOriginator() {
        return originator;
    }

    public static TicketCarer getTicketCarer() {
        return ticketCarer;
    }

    public int getCurrentBuyerId() {
        return currentBuyerId;
    }

    public int getCurrentSellerId() {
        return currentSellerId;
    }

    public int getMinCashAllowance() {
        return minCashAllowance;
    }

    public int getMaxCashAllowance() {
        return maxCashAllowance;
    }

    /**
     * Prevents multiple instances of this class to exist in this project.
     *
     * @Return instance of this class
     */
    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    public void setSellers(Company firstSeller, Company secondSeller) {
        sellers.add(firstSeller);
        sellers.add(secondSeller);
    }
}
