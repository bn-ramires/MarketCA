import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Depot {

    List<Product> stockList;
    List<Product> storageList;
    String owner;
    int cashAllowance;
    int delivery;
    int stockMax;
    int stockMin;
    int storageMax;
    int storageMin;


    public Depot(DepotBuilder builder) {
        this.owner = builder.input.name;
        this.stockList = initStock(builder);
        this.storageList = initStorage(builder);
        this.cashAllowance = builder.input.cashAllowance;
        this.delivery = builder.input.deliveryCost;
        this.stockMax = builder.input.stockMax;
        this.stockMin = builder.input.stockMin;
        this.storageMax = builder.input.storageMax;
        this.storageMin = builder.input.storageMin;
    }

    // Returns a random number between the first and second parameter
    private int getRandomNumber(int max, int min) {

        return new Random().nextInt(max - min + 1) + min;
    }

    private List<Product> initStock(DepotBuilder builder) {

        // Random number between the minimum and maximum amount of allowed stock items
        int numberOfProducts = getRandomNumber(builder.input.stockMax, builder.input.stockMin);

        ArrayList<Product> initializedList = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {

            Product newProduct = new Product.ProductBuilder(builder.input).build();

            initializedList.add(newProduct);
        }

        return initializedList;
    }

    private List<Product> initStorage(DepotBuilder builder) {

        // Random number of products from other companies. Default is 3 to 40 for each.
        int numberOfProducts = getRandomNumber(builder.input.storageMax / 2, builder.input.storageMin / 2);

        ArrayList<Product> initializedList = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {

            Product firstNewProduct = new Product.ProductBuilder(builder.input).build();

            Product secondNewProduct = new Product.ProductBuilder(builder.input).build();

            initializedList.add(firstNewProduct);
            initializedList.add(secondNewProduct);
        }

        Collections.shuffle(initializedList);

        return initializedList;
    }

    public List<Product> getStockList() {
        return stockList;
    }

    public List<Product> getStorageList() {
        return storageList;
    }

    public String getOwner() {
        return owner;
    }

    public int getCashAllowance() {
        return cashAllowance;
    }

    public int getDelivery() {
        return delivery;
    }

    public int getStockMax() {
        return stockMax;
    }

    public int getStockMin() {
        return stockMin;
    }

    public int getStorageMax() {
        return storageMax;
    }

    public int getStorageMin() {
        return storageMin;
    }

    public void setCashAllowance(int cashAllowance) {
        this.cashAllowance = cashAllowance;
    }

    public static class DepotBuilder {

        Company.CompanyBuilder input;

        public DepotBuilder(Company.CompanyBuilder input) {
            this.input = input;
        }

        public Depot build() {
            return new Depot(this);
        }
    }

    @Override
    public String toString() {
        return "Depot{" +
                "stockList=" + stockList +
                ", storageList=" + storageList +
                ", owner='" + owner + '\'' +
                ", cashAllowance=" + cashAllowance +
                ", delivery=" + delivery +
                ", stockMax=" + stockMax +
                ", stockMin=" + stockMin +
                ", storageMax=" + storageMax +
                ", storageMin=" + storageMin +
                '}';
    }
}
