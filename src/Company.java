import java.util.ArrayList;
import java.util.List;

public class Company {

    String name;
    List<Depot> depots;
    int transactions;

    public Company(CompanyBuilder builder) {
        this.name = builder.name;
        this.depots = initDepots(builder);
        this.transactions = builder.transactions;
    }

    private List<Depot> initDepots(CompanyBuilder builder) {

        ArrayList<Depot> initializedList = new ArrayList<>();

        for (int i = 0; i < builder.numberOfDepots; i++) {

            Depot newDepot = new Depot.DepotBuilder(builder).build();

            initializedList.add(newDepot);
        }

        return initializedList;
    }

    public Record makeRecord(List<Ticket> tickets) {
        return null;
    }

    public Report makeFullReport(List<Ticket> tickets) {
        return null;
    }

    public String getName() {
        return name;
    }

    public List<Depot> getDepots() {
        return depots;
    }

    public double getTransactions() {
        return transactions;
    }

    public static class CompanyBuilder {

        String name;
        int numberOfDepots;
        int cashAllowance;
        int transactions;
        int stockMax;
        int stockMin;
        int storageMax;
        int storageMin;
        int productCost;
        int deliveryCost;

        public CompanyBuilder(String name,
                              int numberOfDepots,
                              int cashAllowance,
                              int transactions,
                              int productCost,
                              int deliveryCost,
                              int stockMax,
                              int stockMin,
                              int storageMax,
                              int storageMin) {
            this.name = name;
            this.numberOfDepots = numberOfDepots;
            this.cashAllowance = cashAllowance;
            this.transactions = transactions;
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
                ", depots=" + depots.size() +
                ", transactions=" + transactions +
                '}';
    }
}
