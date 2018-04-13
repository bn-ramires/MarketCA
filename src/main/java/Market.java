import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.Company;
import models.CompanyRecord;
import models.Report;
import purchasing.TicketCarer;
import purchasing.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Market {

    private static Market instance = null;
    private ArrayList<Company> companies;
    private List<CompanyRecord> companyRecords = new ArrayList<>();
    private Report fullReport;
    private TicketCarer carer;
    private boolean isActive = true;

    private Market() {

        // The very first messages to be displayed
        UserInterface.printWelcomeMessage();
        UserInterface.promptToPressEnter();

        // Acquire input and initialize companies based on it
        Database db = new Database();
        JsonObject input = db.getJson();
        initCompanies(input);

    }

    // Following Mark's CA parameters
    public void marksRequirements() {

        while (isActive()) {

            boolean isManualMode = UserInterface.setMode();
            Transaction transaction = Transaction.getInstance();
            transaction.makeTransactions(companies.get(0), companies.get(2), companies.get(1));
            transaction.makeTransactions(companies.get(1), companies.get(0), companies.get(2));
            transaction.makeTransactions(companies.get(2), companies.get(1), companies.get(0));

            setCarer(Transaction.getTicketCarer());

            companies.forEach(company -> {
                CompanyRecord companyRecord = company.makeCompanyRecord(getCarer());
                addCompanyRecords(companyRecord);
            });

            if (isManualMode) {

                int index = UserInterface.selectCompany();
                int numberOfDepots = companies.get(0).getDepots().size();

                if (index != -1) {

                    UserInterface.printCompanyName(companies.get(index).getName());
                    UserInterface.printTitles();

                    companies.get(index).makeFullReport(getCarer()).generateFullReport(numberOfDepots).forEach(depotReport -> {
                        UserInterface.printDepotReport(depotReport);
                    });
                }
            }
            UserInterface.printCompanyResultsTitle();
            companyRecords.forEach(companyRecord -> UserInterface.printRecord(companyRecord));


            int highestCashflowIndex = 0;
            int highestCashflow = 0;
            for (int i = 0; i < companyRecords.size(); i++) {

                if (highestCashflow < companyRecords.get(i).getCashflow()) {
                    highestCashflow = companyRecords.get(i).getCashflow();
                    highestCashflowIndex = i;
                }
            }
            UserInterface.printHighestCashflowCompany(companyRecords.get(highestCashflowIndex));
            setActive(UserInterface.promptToRestart());

        }
        UserInterface.printGoobye();
    }

    private ArrayList<Company> getCompanies() {
        return companies;
    }

    private List<Company> initCompanies(JsonObject input) {

        ArrayList<Company> companies = new ArrayList<>();

        input.getAsJsonArray("companies").forEach(item -> {

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

    private List<CompanyRecord> getCompanyRecords() {
        return companyRecords;
    }

    private void addCompanyRecords(CompanyRecord companyRecord) {
        companyRecords.add(companyRecord);
    }

    private Report getFullReport() {
        return fullReport;
    }

    private void setFullReport(Report fullReport) {
        this.fullReport = fullReport;
    }

    private TicketCarer getCarer() {
        return carer;
    }

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    private void setCarer(TicketCarer carer) {
        this.carer = carer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
