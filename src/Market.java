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

        Transaction transaction = new Transaction(companies.get(0), companies.get(2), companies.get(1));
        transaction.getTicketCarer().getTicketList().forEach(ticket -> {
//            System.out.println(ticket);
        });

//        new Transaction(companies.get(0), companies.get(2), companies.get(1));
//        new Transaction(companies.get(1), companies.get(0), companies.get(2));
//        new Transaction(companies.get(2), companies.get(1), companies.get(0));

    }
}
