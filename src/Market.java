import java.util.ArrayList;
import java.util.List;

public class Market {

    private static Market instance;
    private static ArrayList<Company> companies;
    private static List<Record> companyRecords = new ArrayList<>();
    private static Report fullReport;
    private static TicketCarer carer;

    public Market() {
        getInstance();
        Database db = new Database();
        companies = db.getCompanies();

    }

    // Following Mark's CA parameters
    public void marksRequirements() {

        Transaction.makeTransactions(companies.get(0), companies.get(2), companies.get(1));
        Transaction.makeTransactions(companies.get(1), companies.get(0), companies.get(2));
        Transaction.makeTransactions(companies.get(2), companies.get(1), companies.get(0));

        setCarer(Transaction.getTicketCarer());

        companies.forEach(company -> {
            Record record = company.makeRecord(getCarer());
            addCompanyRecords(record);
        });

        companyRecords.forEach(record -> {
            System.out.println(record.toString());
        });
    }

    private static ArrayList<Company> getCompanies() {
        return companies;
    }

    private static void setCompanies(ArrayList<Company> companies) {
        companies = companies;
    }

    private static List<Record> getCompanyRecords() {
        return companyRecords;
    }

    private static void addCompanyRecords(Record record) {
        companyRecords.add(record);
    }

    private static Report getFullReport() {
        return fullReport;
    }

    private static void setFullReport(Report fullReport) {
        fullReport = fullReport;
    }

    private static TicketCarer getCarer() {
        return carer;
    }

    private static Market getInstance() {
        if (instance == null) {
            return instance = new Market();
        }
        return instance;
    }

    private static void setCarer(TicketCarer carer) {
        carer = carer;
    }
}
