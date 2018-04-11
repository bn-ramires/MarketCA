import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    private JsonObject json = new JsonObject();

    public Database() {

        makeSampleFile();

    }

    private JsonObject makeSampleFile() {

        int numberOfDepots = 100;
        int transactions = 200;
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
        bigA.addProperty("transactions", transactions);
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
        bigB.addProperty("transactions", transactions);
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
        bigC.addProperty("transactions", transactions);
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

        System.out.println(json);
        return json;
    }

        public ArrayList<Company> getCompanies() {

        ArrayList<Company> companies = new ArrayList<>();

        json.getAsJsonArray("companies").forEach(item -> {

            // Creating JSON object
            JsonObject obj = (JsonObject) item;
            // Serializing the CompanyBuilder class with the JSON data
            Company.CompanyBuilder company = new Gson().fromJson(obj, Company.CompanyBuilder.class);
            // Building a new company object
            Company newCompany = company.build();
            // Adding this new company to the list to be returned
            companies.add(newCompany);
        });

        return companies;
    }

    private int getRandomNumber(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
