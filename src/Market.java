import java.util.ArrayList;
import java.util.List;

public class Market {

    private static Market instance = null;
    public ArrayList<Company> companies;
    public List<Record> companyRecords = new ArrayList<>();
    public Report fullReport;
    public TicketCarer carer;

    private Market() {

        Database db = new Database();
        companies = db.getCompanies();

    }

    // Following Mark's CA parameters
    public void marksRequirements() {

        Transaction transaction = Transaction.getInstance();
        transaction.makeTransactions(companies.get(0), companies.get(2), companies.get(1));
        transaction.makeTransactions(companies.get(1), companies.get(0), companies.get(2));
        transaction.makeTransactions(companies.get(2), companies.get(1), companies.get(0));

        setCarer(Transaction.getTicketCarer());

        companies.forEach(company -> {
            Record record = company.makeRecord(getCarer());
            addCompanyRecords(record);
        });

        companyRecords.forEach(record -> {
            System.out.println(record.toString());
        });

        companies.get(0).makeFullReport(getCarer()).generateFullReport();

    }

    private ArrayList<Company> getCompanies() {
        return companies;
    }

    private void setCompanies(ArrayList<Company> companies) {
        companies = companies;
    }

    private List<Record> getCompanyRecords() {
        return companyRecords;
    }

    private void addCompanyRecords(Record record) {
        companyRecords.add(record);
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
