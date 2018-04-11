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

        System.out.println("--------------------------------------------------------------");

        companies.forEach(currentCompany -> {

            System.out.println(currentCompany.toString());

            System.out.println(currentCompany.depots.get(0).toString());

        });

        System.out.println("--------------------------------------------------------------");

    }
}
