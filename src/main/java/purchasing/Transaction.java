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
 * {@link #makeTransactions(Company, Company, Company)}
 * <p>
 * This class is a Singleton. Therefore not meant to be instantiated multiple times
 * as it could possibly cause serious problems for any company involved.
 */
public class Transaction {

    private static Transaction instance = null;
    public ArrayList<Company> sellers = new ArrayList<>();
    public TicketOriginator originator = new TicketOriginator();
    public static TicketCarer ticketCarer = new TicketCarer();
    public int currentBuyerId = -1;
    public int currentSellerId = -1;
    public int minCashAllowance;
    public int maxCashAllowance;

    private Transaction() {
    }

    /**
     * Performs all transactions/operations as required for the assignment.
     *
     * @param buyer        the company assigned to be the buyer on all transactions.
     * @param firstSeller  a company to act as a possible seller on all transactions.
     * @param secondSeller the other possible choice as the seller.
     */
    public void makeTransactions(Company buyer, Company firstSeller, Company secondSeller) {

        setCurrentSellerId(0);
        setCurrentBuyerId(0);
        setMinCashAllowance(buyer.getDepots().get(0).getMinCashAllowance());
        setMaxCashAllowance(buyer.getDepots().get(0).getMaxCashAllowance());

        // Initializing both sellers and shuffling them.
        initSellers(firstSeller, secondSeller);

        // Looping through buyer's depots
        buyer.getDepots().forEach(buyerDepot -> {

            currentBuyerId++;

            // Looping through seller companies
            for (int j = 0; j < getSellers().size(); j++) {

                Company seller = getSellers().get(j);
                boolean notReadyToBuy = false;
                boolean hasBought = false;

                // Break when the current seller depot cannot trade anymore.
                if (notReadyToBuy || hasBought) {
                    break;
                }

                // Looping through sellers's depots
                for (int i = getCurrentSellerId(); i < seller.getDepots().size(); i++) {

                    Depot sellerDepot = seller.getDepots().get(i);

                    // Continue if the buyer depot is capable of performing a purchase.
                    if (isCapableOfBuying(buyerDepot, sellerDepot)) {

                        // The buyer depot's buying goal.
                        int buyingGoal = setBuyingGoal(buyerDepot, sellerDepot);

                        // Continue if the seller depot is capable of selling one or more products.
                        if (isCapableOfSelling(sellerDepot)) {

                            // Settling on a quantity to buy.
                            int quantityToBuy = quantityToBuy(sellerDepot, buyingGoal);
                            // Perform the transaction.
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
     * @param quantity quantity to be bought.
     * @param seller   the depot selling the product(s).
     * @param buyer    the depot buying the product(s).
     */
    protected void buy(int quantity, Depot buyer, Depot seller) {

        int productCost = seller.getStockList().get(0).getPrice();
        int totalCost = (quantity * productCost) + seller.getDeliveryCost();

        pay(totalCost, buyer, seller);
        ship(quantity, buyer, seller);
        generateTicket(quantity, buyer, seller);

    }

    /**
     * Performs payment between two depots.
     *
     * @param totalCost the total cost to be paid.
     * @param seller    the depot receiving the payment.
     * @param buyer     the depot making the payment.
     */
    private void pay(int totalCost, Depot buyer, Depot seller) {

        buyer.setCashAllowance(buyer.getCashAllowance() - totalCost);
        seller.setCashAllowance(seller.getCashAllowance() + totalCost);

    }

    /**
     * Ships products bought from Seller's depot to Buyer's.
     *
     * @param quantity the quantity of products to be shipped.
     * @param seller   the depot shipping the product(s).
     * @param buyer    the depot receiving the product(s).
     */
    private void ship(int quantity, Depot buyer, Depot seller) {

        for (int i = 0; i < quantity; i++) {

            Product productThatWasBought = seller.getStockList().remove(0);
            buyer.getStorageList().add(productThatWasBought);
        }
    }

    /**
     * Generates and stores a ticket for the performed transaction.
     *
     * @param quantity The quantity of products bought or sold.
     * @param seller   the depot who sold the products.
     * @param buyer    the depot that made the purchase.
     * @return the generated ticket.
     */
    protected Ticket generateTicket(int quantity, Depot buyer, Depot seller) {

        int productCost = seller.getStockList().get(0).getPrice();

        getOriginator().setBuyer(buyer.getOwner());
        getOriginator().setSeller(seller.getOwner());
        getOriginator().setBuyerDepotId(getCurrentBuyerId());
        getOriginator().setSellerDepotId(getCurrentSellerId());
        getOriginator().setDeliveryCost(seller.getDeliveryCost());
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
    protected int accountForMaxCashAllowance(int productQty, int productCost, int incomeThreshold) {

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
     * @param buyer  The depot attempting to buy products.
     * @param seller The depot attemtping to sell products.
     * @return The quantity of items this depot is capable of buying.
     */
    private int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.getDeliveryCost();
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
    private int quantityToBuy(Depot seller, int buyerDemand) {

        int stock = seller.getStockList().size();
        int minimum = seller.getStockMin();
        int productsInStock = stock - minimum;
        int productCost = seller.getStockList().get(0).getPrice();
        int deliveryCost = seller.getDeliveryCost();
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
     * @param seller the seller depot used for reference.
     * @param buyer  the buyer depot used for reference.
     * @return A true or false.
     */
    protected Boolean isCapableOfBuying(Depot buyer, Depot seller) {

        int cash = buyer.getCashAllowance();
        int currentAmountOfProductsBought = buyer.getStorageList().size();
        int maxStorageSpace = buyer.getStorageMax();
        int productPrice = seller.getStockList().get(0).getPrice();
        int deliveryCost = seller.getDeliveryCost();
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
     * @param seller the concerned seller depot.
     * @return A true or false.
     */
    private Boolean isCapableOfSelling(Depot seller) {

        int cash = seller.getCashAllowance();
        int stockSpace = seller.getStockList().size();
        int minStockSpace = seller.getStockMin();
        int productCost = seller.getStockList().get(0).getPrice();
        int minimumCharge = productCost + seller.getDeliveryCost();
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

    /**
     * @return The list containing all possible seller companies.
     */
    private List<Company> getSellers() {
        return sellers;
    }

    /**
     * @return The ticket originator.
     */
    private TicketOriginator getOriginator() {
        return originator;
    }

    /**
     * @return The ticket carer, containing a list with all tickets for all transactions.
     */
    public static TicketCarer getTicketCarer() {
        return ticketCarer;
    }

    /**
     * @return The ID number of the current buyer depot in the loop.
     */
    private int getCurrentBuyerId() {
        return currentBuyerId;
    }

    /**
     * @return The ID number of the current seller depot in the loop.
     */
    private int getCurrentSellerId() {
        return currentSellerId;
    }

    /**
     * @return The minimum amount of cash a depot is allowed to have on initialization.
     */
    private int getMinCashAllowance() {
        return minCashAllowance;
    }

    /**
     * @return The maximum amount of cash a depot is allowed to have on initialization.
     */
    private int getMaxCashAllowance() {
        return maxCashAllowance;
    }

    /**
     * @param currentBuyerId The ID number of the current buyer depot in the loop.
     */
    private void setCurrentBuyerId(int currentBuyerId) {
        this.currentBuyerId = currentBuyerId;
    }

    /**
     * @param currentSellerId The ID number of the current seller depot in the loop.
     */
    private void setCurrentSellerId(int currentSellerId) {
        this.currentSellerId = currentSellerId;
    }

    /**
     * @param minCashAllowance The minimum amount of cash a depot is allowed to have on initialization.
     */
    private void setMinCashAllowance(int minCashAllowance) {
        this.minCashAllowance = minCashAllowance;
    }

    /**
     * @param maxCashAllowance The maximum amount of cash a depot is allowed to have on initialization.
     */
    private void setMaxCashAllowance(int maxCashAllowance) {
        this.maxCashAllowance = maxCashAllowance;
    }

    /**
     * Prevents multiple instances of this class to exist in this project.
     *
     * @return instance of this class
     */
    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    /**
     * Places the two possible sellers in a list and shuffles it.
     * Making the output of the program more unpredictable each time it runs.
     *
     * @param firstSeller first possible seller.
     * @param secondSeller second possible seller.
     */
    public void initSellers(Company firstSeller, Company secondSeller) {
        sellers.add(firstSeller);
        sellers.add(secondSeller);
        Collections.shuffle(sellers);
    }
}
