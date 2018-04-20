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

    private int numberOfDepots = 100;
    private int stockMax = 50;
    private int stockMin = 15;
    private int storageMax = 80;
    private int storageMin = 6;
    private int minCashAllowance = 50;
    private int maxCashAllowance = 100;
    private int deliveryCost = getRandomNumber(10, 1);
    private int productCost = getRandomNumber(10, 1);

    private JsonObject json = new JsonObject();

    public Input() {
        makeSampleFile();
    }

    private JsonObject makeSampleFile() {


        System.out.println(productCost);
        JsonObject bigA = new JsonObject();
        bigA.addProperty("name", "BigA");
        bigA.addProperty("numberOfDepots", numberOfDepots);
        bigA.addProperty("productCost", productCost);
        bigA.addProperty("deliveryCost", deliveryCost);
        bigA.addProperty("maxCashAllowance", maxCashAllowance);
        bigA.addProperty("minCashAllowance", minCashAllowance);
        bigA.addProperty("stockMax", stockMax);
        bigA.addProperty("stockMin", stockMin);
        bigA.addProperty("storageMax", storageMax);
        bigA.addProperty("storageMin", storageMin);

        JsonObject bigB = new JsonObject();
        bigB.addProperty("name", "BigB");
        bigB.addProperty("numberOfDepots", numberOfDepots);
        bigB.addProperty("productCost", productCost);
        bigB.addProperty("deliveryCost", deliveryCost);
        bigB.addProperty("maxCashAllowance", maxCashAllowance);
        bigB.addProperty("minCashAllowance", minCashAllowance);
        bigB.addProperty("stockMax", stockMax);
        bigB.addProperty("stockMin", stockMin);
        bigB.addProperty("storageMax", storageMax);
        bigB.addProperty("storageMin", storageMin);

        JsonObject bigC = new JsonObject();
        bigC.addProperty("name", "BigC");
        bigC.addProperty("numberOfDepots", numberOfDepots);
        bigC.addProperty("productCost", productCost);
        bigC.addProperty("deliveryCost", deliveryCost);
        bigC.addProperty("maxCashAllowance", maxCashAllowance);
        bigC.addProperty("minCashAllowance", minCashAllowance);
        bigC.addProperty("stockMax", stockMax);
        bigC.addProperty("stockMin", stockMin);
        bigC.addProperty("storageMax", storageMax);
        bigC.addProperty("storageMin", storageMin);

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
}
