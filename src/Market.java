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

    private Market() {

        Database db = new Database();
        companies = db.getCompanies();

    }

    // Following Mark's CA parameters
    public void marksRequirements() {
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
        if(isManualMode){

            int index = UserInterface.selectCompany();
            if(index != -1) {
                UserInterface.printCompanyName(companies.get(index).getName());
                UserInterface.printTitles("Full Report");
                companies.get(index).makeFullReport(getCarer()).generateFullReport().forEach(depotReport -> {
                    UserInterface.printDepotReport(depotReport);
                });
            }
        }
        UserInterface.printTitles("Company Results");
        companyRecords.forEach(companyRecord -> UserInterface.printRecord(companyRecord));


        int highestCashflowIndex = 0;
        int highestCashflow = 0;
        for(int i = 0; i < companyRecords.size(); i++){

            if(highestCashflow < companyRecords.get(i).getCashflow()){
                highestCashflow = companyRecords.get(i).getCashflow();
               highestCashflowIndex = i;
            }
        }
        UserInterface.printHighestCashflowCompany(companyRecords.get(highestCashflowIndex));



    }

    private ArrayList<Company> getCompanies() {
        return companies;
    }

    private void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
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
}
