import java.util.ArrayList;
import java.util.List;

public class Market {

    ArrayList<Company> companies;
    List<Record> companyRecords = new ArrayList<>();
    Report fullReport;
    TicketCarer carer;

    public Market() {

        Database db = new Database();
        companies = db.getCompanies();

    }

    public void testing() {

        Transaction transaction = new Transaction(companies.get(0), companies.get(2), companies.get(1));

        setCarer(transaction.getTicketCarer());
        companyRecords.add(companies.get(0).makeRecord(getCarer()));
        companyRecords.add(companies.get(1).makeRecord(getCarer()));
        companyRecords.add(companies.get(2).makeRecord(getCarer()));

        companyRecords.forEach(record -> {
            System.out.println(record.toString());
        });

//        new Transaction(companies.get(0), companies.get(2), companies.get(1));
//        new Transaction(companies.get(1), companies.get(0), companies.get(2));
//        new Transaction(companies.get(2), companies.get(1), companies.get(0));

    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public List<Record> getCompanyRecords() {
        return companyRecords;
    }

    public void setCompanyRecords(List<Record> companyRecords) {
        this.companyRecords = companyRecords;
    }

    public Report getFullReport() {
        return fullReport;
    }

    public void setFullReport(Report fullReport) {
        this.fullReport = fullReport;
    }

    public TicketCarer getCarer() {
        return carer;
    }

    public void setCarer(TicketCarer carer) {
        this.carer = carer;
    }
}
