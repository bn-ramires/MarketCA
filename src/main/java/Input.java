import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Random;

/**
 * This class represents the data source needed to carry out all necessary requirements.
 * <p>
 * Sample initiation to meet the CA's requirements.
 * @see Input#makeSampleFile()
 * <p>
 * Data source to be used as input by the program.
 * @see Input#json
 */
public class Input {

    private JsonObject json = new JsonObject();

    public Input() {
        makeSampleFile();
    }

    private JsonObject makeSampleFile() {

        int numberOfDepots = 100;
        int stockMax = 50;
        int stockMin = 15;
        int storageMax = 80;
        int storageMin = 6;
        int cashAllowance = getRandomNumber(100, 50);
        int deliveryCost = getRandomNumber(10, 1);
        int productCost = getRandomNumber(10, 1);

        JsonObject bigA = new JsonObject();
        bigA.addProperty("name", "BigA");
        bigA.addProperty("numberOfDepots", numberOfDepots);
        bigA.addProperty("cashAllowance", cashAllowance);
        bigA.addProperty("productCost", productCost);
        bigA.addProperty("deliveryCost", deliveryCost);
        bigA.addProperty("stockMax", stockMax);
        bigA.addProperty("stockMin", stockMin);
        bigA.addProperty("storageMax", storageMax);
        bigA.addProperty("storageMin", storageMin);

        JsonObject bigB = new JsonObject();
        bigB.addProperty("name", "BigB");
        bigB.addProperty("numberOfDepots", numberOfDepots);
        bigB.addProperty("cashAllowance", cashAllowance);
        bigB.addProperty("productCost", productCost);
        bigB.addProperty("deliveryCost", deliveryCost);
        bigB.addProperty("stockMax", stockMax);
        bigB.addProperty("stockMin", stockMin);
        bigB.addProperty("storageMax", storageMax);
        bigB.addProperty("storageMin", storageMin);

        JsonObject bigC = new JsonObject();
        bigC.addProperty("name", "BigC");
        bigC.addProperty("numberOfDepots", numberOfDepots);
        bigC.addProperty("cashAllowance", cashAllowance);
        bigC.addProperty("productCost", productCost);
        bigC.addProperty("deliveryCost", deliveryCost);
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

    private int getRandomNumber(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public JsonObject getJson() {
        return json;
    }
}
