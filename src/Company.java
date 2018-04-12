import java.util.ArrayList;
import java.util.List;

public class Company {

    String name;
    List<Depot> depots;

    public Company(CompanyBuilder builder) {
        this.name = builder.name;
        this.depots = initDepots(builder);
    }

    private List<Depot> initDepots(CompanyBuilder builder) {

        ArrayList<Depot> initializedList = new ArrayList<>();

        for (int i = 0; i < builder.numberOfDepots; i++) {

            Depot newDepot = new Depot.DepotBuilder(builder).build();

            initializedList.add(newDepot);
        }

        return initializedList;
    }

    public CompanyRecord makeRecord(TicketCarer carer) {
        return new CompanyRecord(name, carer);
    }

    public Report makeFullReport(TicketCarer carer) {
        return new Report(name, carer);
    }

    public String getName() {
        return name;
    }

    public List<Depot> getDepots() {
        return depots;
    }

    public static class CompanyBuilder {

        String name;
        int numberOfDepots;
        int cashAllowance;
        int stockMax;
        int stockMin;
        int storageMax;
        int storageMin;
        int productCost;
        int deliveryCost;

        public CompanyBuilder(String name,
                              int numberOfDepots,
                              int cashAllowance,
                              int productCost,
                              int deliveryCost,
                              int stockMax,
                              int stockMin,
                              int storageMax,
                              int storageMin) {
            this.name = name;
            this.numberOfDepots = numberOfDepots;
            this.cashAllowance = cashAllowance;
            this.productCost = productCost;
            this.deliveryCost = deliveryCost;
            this.stockMax = stockMax;
            this.stockMin = stockMin;
            this.storageMax = storageMax;
            this.storageMin = storageMin;
        }

        public Company build() {
            return new Company(this);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", depots=" + depots +
                '}';
    }
}
