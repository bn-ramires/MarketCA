import java.util.List;

public class Transaction {

    private List<Company> sellers;
    Company buyer;

    private TicketOriginator originator;
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

        Depot randomSellerDepot = getSellers().get(0).depots.get(0);
        Company buyer = getBuyer();
        List<Company> sellers = getSellers();

        buyer.depots.forEach(buyerDepot -> {
            if (isReadyToBuy(buyerDepot, randomSellerDepot)) {

                int buyingGoal = setBuyingGoal(buyerDepot, randomSellerDepot);

                for (int j = 0; j < sellers.size(); j++) {

                    boolean hasBought = false;
                    if (hasBought) {
                        break;
                    }

                    int depotSize = sellers.get(j).depots.size();

                    for (int i = currentSellerId; i < depotSize; i++) {

                        Depot currentDepot = sellers.get(j).depots.get(i);

                        if (isReadyToSell(currentDepot)) {
                            int toBuy = toBuy(currentDepot, buyingGoal);
                            buyProducts(toBuy, buyerDepot, currentDepot);

                            currentSellerId++;
                            hasBought = true;
                            break;
                        }
                    }
                }
            }
        });
    }

    public void buyProducts(int toBuy, Depot buyer, Depot seller) {

        int productCost = seller.stockList.get(0).price;

        originator.setBuyer(buyer.owner);
        originator.setSeller(seller.owner);
        originator.setBuyerDepotId(getCurrentBuyerId());
        originator.setSellerDepotId(getCurrentSellerId());
        originator.setDelivery(seller.delivery);
        originator.setProductCost(productCost);
        originator.setQuantity(toBuy);
        ticketCarer.addTicket(originator.saveTicketState());

        int total = toBuy * productCost;

        for (int i = 0; i < toBuy; i++) {
            Product temp = buyer.stockList.remove(i);
            seller.storageList.add(temp);
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

        if (cash <= 50 && storage <= minimum && cash < totalCost) {
            return false;
        }

        return true;
    }

    public int setBuyingGoal(Depot buyer, Depot seller) {

        int deliveryCost = seller.delivery;
        int unitCost = seller.stockList.get(0).getPrice();
        int stock = buyer.stockList.size();
        int minimum = buyer.stockMin;
        int cash = buyer.cashAllowance;
        int purchasingPower = (int) Math.floor((cash - deliveryCost) / unitCost);
        int spaceAvailable = stock - minimum;

        if (purchasingPower > spaceAvailable) {
            return spaceAvailable;
        }

        return purchasingPower;
    }

    public Boolean isReadyToSell(Depot seller) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        if (stock <= minimum) {
            return false;
        }

        return true;
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
