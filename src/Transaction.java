import java.util.List;

public class Transaction {

    public Transaction(Company buyer, List<Company> sellers) {
        makeTransactions();
    }

    public Ticket makeTransactions(){
        return null;
    }

    public void buyProducts(int quantity, ) {

        int cash = buyer.cashAllowance;
        int storage = buyer.storageList.size();
        int minimum = buyer.storageMin;

        if (cash <= 50 && storage <= minimum) {
            return false;
        }
        return true;
    }

    public Boolean isReadyToBuy(Depot buyer) {

        int cash = buyer.cashAllowance;
        int storage = buyer.storageList.size();
        int minimum = buyer.storageMin;

        if (cash <= 50 && storage <= minimum) {
            return false;
        }
        return true;
    }

    public int setBuyingGoal(Depot buyer, Depot seller) {

        int unitCost = seller.stockList.get(0).getPrice();
        int stock = buyer.stockList.size();
        int minimum = buyer.stockMin;
        int cash = buyer.cashAllowance;
        int purchasingPower = (int) Math.floor(cash / unitCost);
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

    public int settledQuantity(Depot seller, int buyingGoal) {

        int stock = seller.stockList.size();
        int minimum = seller.stockMin;

        int available = stock - minimum;

        if (available < buyingGoal) {
            return available;
        }

        return buyingGoal;
    }
}
