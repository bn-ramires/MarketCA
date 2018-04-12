package purchasing;

import models.Company;
import models.Depot;
import models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Responssible for carrying out all necessary transactions automatically.
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
    public int currentBuyerId;
    public int currentSellerId;

    // Mark's requirements. Will greatly restrict the number of purchases a depot will perform.
    int minimumCashAllowance = 50;

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

        // Looping through buyer's depots
        buyer.depots.forEach(buyerDepot -> {

            System.out.println("!--------------------------");

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
                for (int i = currentSellerId; i < seller.depots.size(); i++) {

                    Depot sellerDepot = seller.depots.get(i);

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
                    }
                }

            }
            System.out.println("/--------------------------");
        });
    }

    /**
     * Performs a purchase between two depots.
     * All necessary considerations are accounted for.
     *
     * @param quatity
     * @param seller
     * @param buyer
     */
    public void buy(int quatity, Depot buyer, Depot seller) {

        int productCost = seller.getStockList().get(0).getPrice();
        int totalCost = (quatity * productCost) + seller.getDelivery();

        pay(totalCost, buyer, seller);
        ship(quatity, buyer, seller);
        generateTicket(quatity, buyer, seller);

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
    private void generateTicket(int quantity, Depot buyer, Depot seller) {

        int productCost = seller.stockList.get(0).price;

        TicketOriginator originator = getOriginator();
        TicketCarer carer = getTicketCarer();

        originator.setBuyer(buyer.getOwner());
        originator.setSeller(seller.getOwner());
        originator.setBuyerDepotId(getCurrentBuyerId());
        originator.setSellerDepotId(getCurrentSellerId());
        originator.setDelivery(seller.getDelivery());
        originator.setProductCost(productCost);
        originator.setQuantity(quantity);
        carer.addTicket(originator.saveTicketState());
    }

    /**
     * Determines if the current Buyer's depot is capable of buying any products.
     *
     * @param seller
     * @param buyer
     * @Return A true or false.
     */
    public Boolean isReadyToBuy(Depot buyer, Depot seller) {

        int cash = buyer.getCashAllowance();
        int storage = buyer.getStorageList().size();
        int minimum = buyer.getStockMin();
        int productPrice = seller.stockList.get(0).getPrice();
        int delivery = seller.delivery;
        int totalCost = productPrice + delivery;

        // Buyer's cash has to be bigger than 50.
        // Also enough to purchase at least a single product and pay for delivery costs.
        // Its storage space bigger than 6 (minimum of 3 products in storage from the other two companies).
        return cash > minimumCashAllowance && storage > minimum && cash >= totalCost;
    }

    /**
     * Determines how many products the Buyer's depot is capable of buying.
     * Ensuring the depot will not be left with its stock capacity below the required minimum amount.
     *
     * @param buyer
     * @param seller
     * @return The quantity of items this depot is capable of buying.
     */
    public int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.getDelivery();
        int productCost = seller.stockList.get(0).getPrice();
        int stock = buyer.stockList.size();
        int minimum = buyer.getStockMin();
        int purchasingPower = buyer.getCashAllowance() - minimumCashAllowance;
        int buyingGoal = (int) Math.floor((purchasingPower - deliveryCost) / productCost);
        int spaceAvailable = stock - minimum;

        if (buyingGoal > spaceAvailable) {
            return spaceAvailable;
        }

        return buyingGoal;
    }

    /**
     * Determines how many products will be bought from the chosen Seller's depot.
     * Ensuring the depot will not be left with its storage capacity below the required minimum amount.
     *
     * @param buyingGoal
     * @param seller
     * @return Quantity of items to be bought.
     */
    public int quantityToBuy(Depot seller, int buyingGoal) {

        int stock = seller.getStockList().size();
        int minimum = seller.getStockMin();

        int available = stock - minimum;

        if (available < buyingGoal) {
            return available;
        }

        return buyingGoal;
    }

    /**
     * Determines if the current Seller's depot is capable of selling any products.
     *
     * @param seller
     * @Return A true or false.
     */
    public Boolean isReadyToSell(Depot seller) {

        int stock = seller.getStockList().size();
        int minimum = seller.getStockMin();

        // It can only sell products if its stock capacity is above the minimum required.
        return stock > minimum;
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
