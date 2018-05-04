package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This is a model for a Depot and it holds all necessary information about it.
 */
public class Depot {

    List<Product> stockList;
    List<Product> storageList;
    String owner;
    int cashAllowance;
    int minCashAllowance;
    int maxCashAllowance;
    int deliveryCost;
    int stockMax;
    int stockMin;
    int storageMax;
    int storageMin;


    public Depot(DepotBuilder builder) {
        this.owner = builder.input.name;
        this.stockList = initStock(builder);
        this.storageList = initStorage(builder);
        this.minCashAllowance = builder.input.minCashAllowance;
        this.maxCashAllowance = builder.input.maxCashAllowance;
        this.cashAllowance = getRandomNumber(getMaxCashAllowance(), getMinCashAllowance());
        this.deliveryCost = builder.input.deliveryCost;
        this.stockMax = builder.input.stockMax;
        this.stockMin = builder.input.stockMin;
        this.storageMax = builder.input.storageMax;
        this.storageMin = builder.input.storageMin;
    }

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

        // All products are created and named accordingly.
        for (int i = 0; i < numberOfProducts; i++) {

            Product firstNewProduct = new Product.ProductBuilder(builder.input).build();
            Product secondNewProduct = new Product.ProductBuilder(builder.input).build();

            if (getOwner().equals("BigA")) {
                firstNewProduct.setBrand("BigB");
                secondNewProduct.setBrand("BigC");
            } else if (getOwner().equals("BigB")) {
                firstNewProduct.setBrand("BigA");
                secondNewProduct.setBrand("BigC");
            } else if (getOwner().equals("BigC")) {
                firstNewProduct.setBrand("BigA");
                secondNewProduct.setBrand("BigB");
            }

            initializedList.add(firstNewProduct);
            initializedList.add(secondNewProduct);
        }

        // Shuffling the list to be less predictable.
        Collections.shuffle(initializedList);

        return initializedList;
    }


    /**
     * @return a list of products in stock. (products from this company).
     */
    public List<Product> getStockList() {
        return stockList;
    }

    /**
     * @return a list of products in storage. (products from other companies).
     */
    public List<Product> getStorageList() {
        return storageList;
    }

    /**
     * @return the name of the company that owns this depot.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return the cash allowance of this depot.
     */
    public int getCashAllowance() {
        return cashAllowance;
    }

    /**
     * @return the deliveryCost cost this depot charges others for transactions.
     */
    public int getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * @return the maximum amount of products this depot can have in stock.
     */
    public int getStockMax() {
        return stockMax;
    }

    /**
     * @return the minimum amount of products this depot can have in stock.
     */
    public int getStockMin() {
        return stockMin;
    }

    /**
     * @return the maximum amount of products this depot can have in storage.
     */
    public int getStorageMax() {
        return storageMax;
    }

    /**
     * @return the minimum amount of products this depot can have in storage.
     */
    public int getStorageMin() {
        return storageMin;
    }

    /**
     * @param cashAllowance the cash allowance of this depot.
     */
    public void setCashAllowance(int cashAllowance) {
        this.cashAllowance = cashAllowance;
    }

    /**
     * @param stockList the list of products in stock. (products from this company).
     */
    public void setStockList(List<Product> stockList) {
        this.stockList = stockList;
    }

    /**
     * @param storageList the list of products in storage. (products from other companies).
     */
    public void setStorageList(List<Product> storageList) {
        this.storageList = storageList;
    }

    /**
     * @param owner the name of the company that owns this depot.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @param deliveryCost the price this depot charges others for transactions.
     */
    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * @param stockMax the maximum amount of products this depot can have in stock.
     */
    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    /**
     * @param stockMin the minimum amount of products this depot can have in stock.
     */
    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    /**
     * @param storageMax the maximum amount of products this depot can have in storage.
     */
    public void setStorageMax(int storageMax) {
        this.storageMax = storageMax;
    }

    /**
     * @param storageMin the minimum amount of products this depot can have in storage.
     */
    public void setStorageMin(int storageMin) {
        this.storageMin = storageMin;
    }

    /**
     * @return the minimum cash allowance this depot can possibly have, prior to transactions.
     */
    public int getMinCashAllowance() {
        return minCashAllowance;
    }

    /**
     * @return the maximum cash allowance this depot can possibly have, prior to transactions.
     */
    public int getMaxCashAllowance() {
        return maxCashAllowance;
    }

    /**
     * The builder pattern for a Depot.
     */
    public static class DepotBuilder {

        Company.CompanyBuilder input;

        public DepotBuilder(Company.CompanyBuilder input) {
            this.input = input;
        }

        /**
         * @return builds a Depot.
         */
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
                ", deliveryCost=" + deliveryCost +
                ", stockMax=" + stockMax +
                ", stockMin=" + stockMin +
                ", storageMax=" + storageMax +
                ", storageMin=" + storageMin +
                '}';
    }
}
