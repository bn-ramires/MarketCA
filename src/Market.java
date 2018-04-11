import java.util.ArrayList;
import java.util.List;

public class Market {

    ArrayList<Company> companies;
    List<Record> companyRecords;
    Report fullReport;

    public Market() {

        Database db = new Database();
        companies = db.getCompanies();

    }

    public void testing() {


        new Transaction(companies.get(0), companies.get(2), companies.get(1));
        new Transaction(companies.get(1), companies.get(0), companies.get(2));
        new Transaction(companies.get(2), companies.get(1), companies.get(0));

        System.out.println("--------------------------------------------------------------");

    }
}
