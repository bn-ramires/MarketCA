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

    public static int getRandomNumber(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;

    }

    public JsonObject getJson() {
        return json;
    }

    public int getNumberOfDepots() {
        return numberOfDepots;
    }

    public void setNumberOfDepots(int numberOfDepots) {
        this.numberOfDepots = numberOfDepots;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public int getStorageMax() {
        return storageMax;
    }

    public void setStorageMax(int storageMax) {
        this.storageMax = storageMax;
    }

    public int getStorageMin() {
        return storageMin;
    }

    public void setStorageMin(int storageMin) {
        this.storageMin = storageMin;
    }

    public int getMinCashAllowance() {
        return minCashAllowance;
    }

    public void setMinCashAllowance(int minCashAllowance) {
        this.minCashAllowance = minCashAllowance;
    }

    public int getMaxCashAllowance() {
        return maxCashAllowance;
    }

    public void setMaxCashAllowance(int maxCashAllowance) {
        this.maxCashAllowance = maxCashAllowance;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}
