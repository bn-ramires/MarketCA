import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private ArrayList<Company> sellers = new ArrayList<>();
    Company buyer;

    private TicketOriginator originator = new TicketOriginator();
    private TicketCarer ticketCarer = new TicketCarer();

    private int currentBuyerId;
    private int currentSellerId;

    public Transaction(Company buyer, Company firstSeller, Company secondSeller) {
        this.sellers.add(firstSeller);
        this.sellers.add(secondSeller);
        this.buyer = buyer;

        makeTransactions();
    }

    public void makeTransactions() {

        currentSellerId = 0;

        Company buyer = getBuyer();
        List<Company> sellers = getSellers();

        // Looping through buyer's depots
        buyer.depots.forEach(buyerDepot -> {

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
                            System.out.println("To buy: " + toBuy);
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
        });
    }

    public void buyProducts(int toBuy, Depot buyer, Depot seller) {

        int productCost = seller.stockList.get(0).price;

        originator.setBuyer(buyer.getOwner());
        originator.setSeller(seller.getOwner());
        originator.setBuyerDepotId(getCurrentBuyerId());
        originator.setSellerDepotId(getCurrentSellerId());
        originator.setDelivery(seller.getDelivery());
        originator.setProductCost(productCost);
        originator.setQuantity(toBuy);
        ticketCarer.addTicket(originator.saveTicketState());

        int total = toBuy * productCost;

        for (int i = 0; i < toBuy; i++) {
//            System.out.println("To buy:" + toBuy);
//            System.out.println("Stock List Size: " + seller.stockList.size());

            System.out.println("buyProducts() Stock size: " + seller.stockList.size());
            System.out.println("buyProducts() Stock isEmpty?!: " + seller.stockList.isEmpty());
            Product temp = seller.getStockList().remove(i);
            buyer.getStockList().add(temp);
        }

        buyer.setCashAllowance(buyer.getCashAllowance() - total);
        seller.setCashAllowance(seller.getCashAllowance() + total);

    }

    public Boolean isReadyToBuy(Depot buyer, Depot seller) {

        int cash = buyer.cashAllowance;
        int storage = buyer.storageList.size();
        int minimum = buyer.storageMin;
        int productPrice = seller.stockList.get(0).getPrice();
        int delivery = seller.delivery;
        int totalCost = productPrice + delivery;

        if (cash <= 50 || storage <= minimum || cash < totalCost) {
            return false;
        }

        return true;
    }

    public int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.getDelivery();
        int productCost = seller.stockList.get(0).getPrice();
        int stock = buyer.stockList.size();
        int minimum = buyer.stockMin;
        int cash = buyer.cashAllowance;
        int purchasingPower = (int) Math.floor((cash - deliveryCost) / productCost);
        System.out.println("Product cost: €" + productCost);
        System.out.println("Delivery cost: €" + deliveryCost);
        System.out.println("Total cash: €" + cash);
        System.out.println("Purchasing power: " + purchasingPower);
        int spaceAvailable = stock - minimum;

        if (purchasingPower > spaceAvailable) {
            return spaceAvailable;
        }

        return purchasingPower;
    }

    public int toBuy(Depot seller, int buyingGoal) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        int available = stock - minimum;

        if (available < buyingGoal) {
            return available;
        }

        return buyingGoal;
    }

    public Boolean isReadyToSell(Depot seller) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        System.out.println("isReadyToSell STOCK: " + stock);
        System.out.println("isReadyToSell MINIMUM: " + minimum);
        if (stock <= minimum) {
            return false;
        }

        return true;
    }

    public List<Company> getSellers() {
        return sellers;
    }

    public Company getBuyer() {
        return buyer;
    }

    public TicketOriginator getOriginator() {
        return originator;
    }

    public TicketCarer getTicketCarer() {
        return ticketCarer;
    }

    public int getCurrentBuyerId() {
        return currentBuyerId;
    }

    public int getCurrentSellerId() {
        return currentSellerId;
    }
}
