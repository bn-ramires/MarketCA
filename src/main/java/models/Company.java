package models;

import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a model for a company and it holds all necessary information about it.
 */
public class Company {

    private String name;
    private List<Depot> depots;

    public Company(CompanyBuilder builder) {
        this.name = builder.name;
        this.depots = initDepots(builder);
    }

    /**
     * Initializes the list of depots of this company.
     *
     * @param builder required input data for depot instantiation.
     * @return Initialized list of depot objects.
     */
    private List<Depot> initDepots(CompanyBuilder builder) {

        ArrayList<Depot> initializedList = new ArrayList<>();

        for (int i = 0; i < builder.numberOfDepots; i++) {

            Depot newDepot = new Depot.DepotBuilder(builder).build();

            initializedList.add(newDepot);
        }

        return initializedList;
    }

    /**
     * Generates a company record containing information a company's transactions.
     *
     * @param carer list of tickets required for CompanyRecord objects to be made.
     * @return a new company record.
     */
    public CompanyRecord makeCompanyRecord(TicketCarer carer) {
        return new CompanyRecord(getName(), carer);
    }

    /**
     * Generates a report containing detailed info on transactions done by each depot.
     *
     * @param carer list of tickets required for a report to be made.
     * @return a new report object.
     */
    public Report makeFullReport(TicketCarer carer) {
        return new Report(getName(), getDepots(), carer);
    }

    /**
     * @return the company's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return a list with all depots of a company.
     */
    public List<Depot> getDepots() {
        return depots;
    }

    /**
     * A builder pattern for a Company.
     */
    public static class CompanyBuilder {

        String name;
        int numberOfDepots;
        int stockMax;
        int stockMin;
        int storageMax;
        int storageMin;
        int minCashAllowance;
        int maxCashAllowance;
        int productCost;
        int deliveryCost;

        public CompanyBuilder(String name,
                              int numberOfDepots,
                              int stockMax,
                              int stockMin,
                              int storageMax,
                              int storageMin,
                              int minCashAllowance,
                              int maxCashAllowance,
                              int productCost,
                              int deliveryCost) {
            this.name = name;
            this.numberOfDepots = numberOfDepots;
            this.stockMax = stockMax;
            this.stockMin = stockMin;
            this.storageMax = storageMax;
            this.storageMin = storageMin;
            this.minCashAllowance = minCashAllowance;
            this.maxCashAllowance = maxCashAllowance;
            this.productCost = productCost;
            this.deliveryCost = deliveryCost;
        }

        /**
         * @return a new Company object as per builder pattern practices.
         */
        public Company build() {
            return new Company(this);
        }
    }

    @Override
    public String toString() {
        return "models.Company{" +
                "name='" + name + '\'' +
                ", depots=" + depots.size() +
                '}';
    }
}
