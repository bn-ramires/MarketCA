import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private static Transaction instance;
    private static ArrayList<Company> sellers = new ArrayList<>();
    private static TicketOriginator originator = new TicketOriginator();
    private static TicketCarer ticketCarer = new TicketCarer();
    private static int currentBuyerId;
    private static int currentSellerId;

    private Transaction() {
        getInstance();
    }

    public static void makeTransactions(Company buyer, Company firstSeller, Company secondSeller) {

        currentSellerId = 0;
        setSellers(firstSeller, secondSeller);
        List<Company> sellers = getSellers();

        // Looping through buyer's depots
        buyer.depots.forEach(buyerDepot -> {
            System.out.println("!--------------------------");
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
                            int toBuy = toBuy(sellerDepot, buyingGoal);
                            buyProducts(toBuy, buyerDepot, sellerDepot);

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

    private static void buyProducts(int toBuy, Depot buyer, Depot seller) {

        int productCost = seller.stockList.get(0).price;
        int totalCost = (toBuy * productCost) + seller.delivery;
        TicketOriginator originator = getOriginator();
        TicketCarer carer = getTicketCarer();

        originator.setBuyer(buyer.getOwner());
        originator.setSeller(seller.getOwner());
        originator.setBuyerDepotId(getCurrentBuyerId());
        originator.setSellerDepotId(getCurrentSellerId());
        originator.setDelivery(seller.getDelivery());
        originator.setProductCost(productCost);
        originator.setQuantity(toBuy);
        carer.addTicket(originator.saveTicketState());

        for (int i = 0; i < toBuy; i++) {

            Product temp = seller.getStockList().remove(0);
            buyer.getStockList().add(temp);
        }

        buyer.setCashAllowance(buyer.getCashAllowance() - totalCost);
        seller.setCashAllowance(seller.getCashAllowance() + totalCost);

    }

    private static Boolean isReadyToBuy(Depot buyer, Depot seller) {

        int cash = buyer.cashAllowance;
        int storage = buyer.storageList.size();
        int minimum = buyer.storageMin;
        int productPrice = seller.stockList.get(0).getPrice();
        int delivery = seller.delivery;
        int totalCost = productPrice + delivery;

        return cash > 50 && storage > minimum && cash >= totalCost;
    }

    private static int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.getDelivery();
        int productCost = seller.stockList.get(0).getPrice();
        int stock = buyer.stockList.size();
        int minimum = buyer.stockMin;
        int purchasingPower = buyer.cashAllowance - 50;
        int buyingGoal = (int) Math.floor((purchasingPower - deliveryCost) / productCost);
        int spaceAvailable = stock - minimum;

        if (buyingGoal > spaceAvailable) {
            return spaceAvailable;
        }

        return buyingGoal;
    }

    private static int toBuy(Depot seller, int buyingGoal) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        int available = stock - minimum;

        if (available < buyingGoal) {
            return available;
        }

        return buyingGoal;
    }

    private static Boolean isReadyToSell(Depot seller) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        return stock > minimum;
    }

    private static List<Company> getSellers() {
        return sellers;
    }

    public static TicketOriginator getOriginator() {
        return originator;
    }

    public static TicketCarer getTicketCarer() {
        return ticketCarer;
    }

    private static int getCurrentBuyerId() {
        return currentBuyerId;
    }

    private static int getCurrentSellerId() {
        return currentSellerId;
    }

    private static Transaction getInstance() {
        if (instance == null) {
            return Transaction.instance = new Transaction();
        }
        return instance;
    }

    public static void setSellers(Company firstSeller, Company secondSeller) {
        sellers.add(firstSeller);
        sellers.add(secondSeller);
    }
}
