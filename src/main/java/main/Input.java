package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Random;

/**
 * This class represents the data source needed to carry out all necessary requirements.
 * <p>
 * Sample initiation to meet the CA's requirements.
 *
 * @see Input#makeSampleFile()
 * <p>
 * Data source to be used as input by the program.
 * @see Input#json
 */
public class Input {

    private int numberOfDepots;
    private int stockMax;
    private int stockMin;
    private int storageMax;
    private int storageMin;
    private int minCashAllowance;
    private int maxCashAllowance;
    private int deliveryCost;
    private int productCost;
    private JsonObject json = new JsonObject();

    public Input() {

        numberOfDepots = 100;
        stockMax = 50;
        stockMin = 15;
        storageMax = 80;
        storageMin = 6;
        minCashAllowance = 50;
        maxCashAllowance = 100;
        deliveryCost = getRandomNumber(10, 1);
        productCost = getRandomNumber(10, 1);
    }

    /**
     * Making an arbitrary JSON object with the necessary requirements for the assignment.
     *
     * @return JsonObject to be used as the data source for the program.
     */
    public JsonObject makeSampleFile() {

        JsonObject bigA = new JsonObject();
        bigA.addProperty("name", "BigA");
        bigA.addProperty("numberOfDepots", getNumberOfDepots());
        bigA.addProperty("productCost", getProductCost());
        bigA.addProperty("deliveryCost", getDeliveryCost());
        bigA.addProperty("maxCashAllowance", getMaxCashAllowance());
        bigA.addProperty("minCashAllowance", getMinCashAllowance());
        bigA.addProperty("stockMax", getStockMax());
        bigA.addProperty("stockMin", getStockMin());
        bigA.addProperty("storageMax", getStorageMax());
        bigA.addProperty("storageMin", getStorageMin());

        JsonObject bigB = new JsonObject();
        bigB.addProperty("name", "BigB");
        bigB.addProperty("numberOfDepots", getNumberOfDepots());
        bigB.addProperty("productCost", getProductCost());
        bigB.addProperty("deliveryCost", getDeliveryCost());
        bigB.addProperty("maxCashAllowance", getMaxCashAllowance());
        bigB.addProperty("minCashAllowance", getMinCashAllowance());
        bigB.addProperty("stockMax", getStockMax());
        bigB.addProperty("stockMin", getStockMin());
        bigB.addProperty("storageMax", getStorageMax());
        bigB.addProperty("storageMin", getStorageMin());

        JsonObject bigC = new JsonObject();
        bigC.addProperty("name", "BigC");
        bigC.addProperty("numberOfDepots", getNumberOfDepots());
        bigC.addProperty("productCost", getProductCost());
        bigC.addProperty("deliveryCost", getDeliveryCost());
        bigC.addProperty("maxCashAllowance", getMaxCashAllowance());
        bigC.addProperty("minCashAllowance", getMinCashAllowance());
        bigC.addProperty("stockMax", getStockMax());
        bigC.addProperty("stockMin", getStockMin());
        bigC.addProperty("storageMax", getStorageMax());
        bigC.addProperty("storageMin", getStorageMin());

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(bigA);
        jsonArray.add(bigB);
        jsonArray.add(bigC);

        json.add("companies", jsonArray);

        return json;
    }

    /**
     * Generates a random number between two parameters.
     *
     * @param max maximum result.
     * @param min minimum result.
     * @return randomized number
     */
    public static int getRandomNumber(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * @return JsonObject with necessary information of all companies.
     */
    public JsonObject getJson() {
        return json;
    }

    /**
     * @return the number of depots to be created. Required: 100.
     */
    public int getNumberOfDepots() {
        return numberOfDepots;
    }

    /**
     * @param numberOfDepots the number of depots to be created. Required: 100.
     */
    public void setNumberOfDepots(int numberOfDepots) {
        this.numberOfDepots = numberOfDepots;
    }

    /**
     * @return the maximum size of a depot's stock. Required: 50.
     */
    public int getStockMax() {
        return stockMax;
    }

    /**
     * @param stockMax the maximum size of a depot's stock. Required: 50.
     */
    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    /**
     * @return the minimum size of a depot's stock. Required: 15.
     */
    public int getStockMin() {
        return stockMin;
    }

    /**
     * @param stockMin the minimum size of a depot's stock. Required: 15.
     */
    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    /**
     * @return the maximum size of a depot's storage (other company's products). Required: 40.
     */
    public int getStorageMax() {
        return storageMax;
    }

    /**
     * @param storageMax the maximum size of a depot's storage (other company's products). Required: 40.
     */
    public void setStorageMax(int storageMax) {
        this.storageMax = storageMax;
    }

    /**
     * @return the minimum size of a depot's storage (other company's products). Required: 3.
     */
    public int getStorageMin() {
        return storageMin;
    }

    /**
     * @param storageMin the minimum size of a depot's storage (other company's products). Required: 3.
     */
    public void setStorageMin(int storageMin) {
        this.storageMin = storageMin;
    }

    /**
     * @return the minimum amount of cash a depot will be initialized with. Required: 50.
     */
    public int getMinCashAllowance() {
        return minCashAllowance;
    }

    /**
     * @param minCashAllowance the minimum amount of cash a depot will be initialized with. Required: 50.
     */
    public void setMinCashAllowance(int minCashAllowance) {
        this.minCashAllowance = minCashAllowance;
    }

    /**
     * @return the maximum amount of cash a depot will be initialized with. Required: 100.
     */
    public int getMaxCashAllowance() {
        return maxCashAllowance;
    }

    /**
     * @param maxCashAllowance the maximum amount of cash a depot will be initialized with. Required: 100.
     */
    public void setMaxCashAllowance(int maxCashAllowance) {
        this.maxCashAllowance = maxCashAllowance;
    }

    /**
     * @return the cost of delivery for each transaction. Required: 1-10.
     */
    public int getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * @param deliveryCost the cost of delivery for each transaction. Required: 1-10.
     */
    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * @return the cost of products for each transaction. Required: 1-10.
     */
    public int getProductCost() {
        return productCost;
    }

    /**
     * @return the cost of products for each transaction. Required: 1-10.
     */
    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}
