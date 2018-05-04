package main;

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
 *
 * All CA's requirements are attempted to be fulfilled by simply calling this class.
 * Also, this class is a singleton and can't be initialized multiple times.
 */
public class Market {

    private static Market instance = null;
    private ArrayList<Company> companies = new ArrayList<>();
    private List<CompanyRecord> companyRecords = new ArrayList<>();
    private TicketCarer carer;

    private Market() {

        // The very first messages to be displayed
        UserInterface.printWelcomeMessage();
        UserInterface.promptToPressEnter();

        // Acquire input and initialize companies based on it
        Input source = new Input();
        source.makeSampleFile();
        JsonObject input = source.getJson();
        initCompanies(input);

        init();

    }

    /**
     * This method is responsible for initializing the whole logic behind the assignment.
     * <p>
     * All requirements should be fulfilled by calling this. Meaning all transactions should be performed,
     * statistics should be generated, messages should be displayed to the screen, etc.
     */
    public void init() {

        // Prompts user to choose between auto/manual mode.
        boolean isManualMode = UserInterface.setMode();

        // Making all necessary transactions. All companies trade between each other.
        Transaction transaction = Transaction.getInstance();
        transaction.makeTransactions(
                getCompanies().get(0),
                getCompanies().get(2),
                getCompanies().get(1));
        transaction.makeTransactions(
                getCompanies().get(1),
                getCompanies().get(0),
                getCompanies().get(2));
        transaction.makeTransactions(
                getCompanies().get(2),
                getCompanies().get(1),
                getCompanies().get(0));

        // Grabbing the ticket carer for future usage.
        setCarer(transaction.getTicketCarer());

        // Generating financial records for all companies after transactions are made.
        initCompanyRecords();

        // If the user chooses manual mode.
        if (isManualMode) {

            // User selects which company they want to see detailed info of.
            int index = UserInterface.selectCompany();

            // Prints a banner to the screen
            UserInterface.printCompanyName(getCompanies().get(index).getName());
            UserInterface.printFullReportTitle();

            // Prints a list of depot reports, only for the selected company.
            Report report = getCompanies().get(index).makeFullReport(getCarer());
            report.getDepotReportList().forEach(depotReport -> UserInterface.printDepotReport(depotReport));

        } // If the user chooses automatic mode.

        // Prints financial records for all companies involved in the market
        UserInterface.printCompanyResultsTitle();
        getCompanyRecords().forEach(companyRecord -> UserInterface.printRecord(companyRecord));

        // Prints the company with the best cash flow.
        calcWinnerCompany();

        // Prints a goodbye message.
        UserInterface.printGoodbye();
    }

    /**
     * Initializing the list with the company records of all companies.
     *
     * @see Market#companyRecords the list containing all data at the end.
     */
    private void initCompanyRecords() {
        getCompanies().forEach(company -> {
            CompanyRecord companyRecord = company.makeCompanyRecord(getCarer());
            companyRecords.add(companyRecord);
        });
    }

    /**
     * Calculates the winner company. Based on which company has the best cash flow at the end.
     */
    private void calcWinnerCompany() {

        int winnerCompany = 0;
        int highestCashflow = 0;
        for (int i = 0; i < getCompanyRecords().size(); i++) {

            if (highestCashflow < getCompanyRecords().get(i).getCashFlow()) {
                highestCashflow = getCompanyRecords().get(i).getCashFlow();
                winnerCompany = i;
            }
        }

        // Printing winner
        UserInterface.printHighestCashflowCompany(getCompanyRecords().get(winnerCompany));

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

    /**
     * @return The ticket carer object. Used to calculate company records and depot reports.
     */
    private TicketCarer getCarer() {
        return carer;
    }

    /**
     * @param carer Object used to calculate company records and depot reports.
     */
    private void setCarer(TicketCarer carer) {
        this.carer = carer;
    }

    /**
     * @return An instance of this class. (Singleton pattern).
     */
    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    /**
     * @return a list of all companies.
     */
    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /**
     * @return a list of all company records.
     */
    public List<CompanyRecord> getCompanyRecords() {
        return companyRecords;
    }
}
