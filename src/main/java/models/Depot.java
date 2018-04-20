package models;

import java.util.*;

/**
 * This is a model for a Depot and it holds all necessary information about it.
 * <p>
 * Lists of both the depot's and other company's products. Randomized as required.
 *
 * @see Depot#stockList
 * @see Depot#storageList
 * <p>
 * The company that owns this depot.
 * @see Depot#owner
 * <p>
 * Cash allowance. Restricted and randomized as required.
 * @see Depot#cashAllowance
 * <p>
 * Delivery cost for anyone buying from this depot.
 * @see Depot#delivery
 * <p>
 * Restrictions as required for the depot's own products and purchased ones.
 * @see Depot#stockMax
 * @see Depot#stockMin
 * @see Depot#storageMax
 * @see Depot#storageMin
 */
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
        this.cashAllowance = getRandomNumber(builder.input.maxCashAllowance, builder.input.minCashAllowance);
        this.delivery = builder.input.deliveryCost;
        this.stockMax = builder.input.stockMax;
        this.stockMin = builder.input.stockMin;
        this.storageMax = builder.input.storageMax;
        this.storageMin = builder.input.storageMin;
    }

    // This constructor is for testing purposes
    public Depot() {
    }

    /**
     * Generates a random number between two parameters.
     *
     * @param max maximum result.
     * @param min minimum result.
     * @return randomized number
     */
    private int getRandomNumber(int max, int min) {

        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * Initialized depot's stock list as required.
     * <p>
     * Requirement: minimum of 15 products and maximum of 50.
     *
     * @param builder required input from original JSON file.
     * @return list of this depot's products.
     */
    private List<Product> initStock(DepotBuilder builder) {

        // Random number between the minimum and maximum amount of allowed stock items
        int numberOfProducts = getRandomNumber(builder.input.stockMax, builder.input.stockMin);

        List<Product> initializedList = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {

            Product newProduct = new Product.ProductBuilder(builder.input).build();

            initializedList.add(newProduct);
        }

        return initializedList;
    }

    /**
     * Initialized depot's storage list as required.
     * <p>
     * Requirement: minimum of 3 products and maximum of 40. For each company.
     *
     * @param builder required input from original JSON file.
     * @return list of other depot's products.
     */
    private List<Product> initStorage(DepotBuilder builder) {

        // Random number of products from other companies. Default is 3 to 40 for each.
        int numberOfProducts = getRandomNumber(builder.input.storageMax / 2, builder.input.storageMin / 2);

        List<Product> initializedList = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {

            Product firstNewProduct = new Product.ProductBuilder(builder.input).build();

            Product secondNewProduct = new Product.ProductBuilder(builder.input).build();

            initializedList.add(firstNewProduct);
            initializedList.add(secondNewProduct);
        }

        // Randomizing the list
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

    public void setStockList(List<Product> stockList) {
        this.stockList = stockList;
    }

    public void setStorageList(List<Product> storageList) {
        this.storageList = storageList;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public void setStorageMax(int storageMax) {
        this.storageMax = storageMax;
    }

    public void setStorageMin(int storageMin) {
        this.storageMin = storageMin;
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
        return "models.Depot{" +
                "stockList=" + stockList.size() +
                ", storageList=" + storageList.size() +
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
