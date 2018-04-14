import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.Company;
import models.CompanyRecord;
import models.Report;
import purchasing.TicketCarer;
import purchasing.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for initializing the market. It effectively works as the main class.
 * <p>
 * All CA's requirements are attempted to be fulfilled by simply calling this class.
 * Also, this class is a singleton and can't be initialized multiple times.
 * <p>
 * @see Market#init() Initialization based on CA's requirements.
 * @see Market#instance instance of this class. (singleton pattern).
 * @see Market#companies list of all company objects.
 * @see Market#companyRecords list of all company's financial records.
 * @see Market#carer holds all tickets for all transactions performed.
 * @see Market#isActive determined if the software should keep running or not.
 */
public class Market {

    private static Market instance = null;
    private ArrayList<Company> companies = new ArrayList<>();
    private List<CompanyRecord> companyRecords = new ArrayList<>();
    private TicketCarer carer;
    private boolean isActive = true;

    private Market() {

        // The very first messages to be displayed
        UserInterface.printWelcomeMessage();
        UserInterface.promptToPressEnter();

        // Acquire input and initialize companies based on it
        Input db = new Input();
        JsonObject input = db.getJson();
        initCompanies(input);

        init();

    }

    // Following Mark's CA parameters
    public void init() {

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

                    // Generate a Report for the selected company
                    Report report = companies.get(index).makeFullReport(getCarer());
                    // Print the report, containing details of all depots
                    report.getReportList().forEach(depotReport -> {
                        UserInterface.printDepotReport(depotReport);
                    });
                }
                // Print financial records for all companies involved in the market
                UserInterface.printCompanyResultsTitle();
                companyRecords.forEach(companyRecord -> UserInterface.printRecord(companyRecord));
            }

            // Print financial records for all companies involved in the market
            UserInterface.printCompanyResultsTitle();
            companyRecords.forEach(companyRecord -> UserInterface.printRecord(companyRecord));


            // Determining the company that has the best cashflow
            int winnerCompany = 0;
            int highestCashflow = 0;
            for (int i = 0; i < companyRecords.size(); i++) {

                if (highestCashflow < companyRecords.get(i).getCashflow()) {
                    highestCashflow = companyRecords.get(i).getCashflow();
                    winnerCompany = i;
                }
            }

            // Printing winner
            UserInterface.printHighestCashflowCompany(companyRecords.get(winnerCompany));
            // Prompting user to start a new transaction or quit
            setActive(UserInterface.promptToRestart());

        }

        UserInterface.printGoobye();
    }

    private ArrayList<Company> getCompanies() {
        return companies;
    }

    /**
     * Initializes all companies with all their necessary data and stores it in a list.
     *
     * @param input JSON object with initialization data for all companies.
     */
    private void initCompanies(JsonObject input) {

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
    }

    private List<CompanyRecord> getCompanyRecords() {
        return companyRecords;
    }

    private void addCompanyRecords(CompanyRecord companyRecord) {
        companyRecords.add(companyRecord);
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

    private boolean isActive() {
        return isActive;
    }

    private void setActive(boolean active) {
        isActive = active;
    }
}
