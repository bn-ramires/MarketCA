package models;

/**
 * This is a model for a depot's report. Containing financial information about
 * transactions carried out by a depot.
 * <p>
 * Depot's ID.
 * @see DepotReport#depotId
 * <p>
 * Products sold by it.
 * @see DepotReport#prodSold
 * <p>
 * Products bought by it.
 * @see DepotReport#prodBought
 * <p>
 * How much money it has made.
 * @see DepotReport#income
 * <p>
 * How much money it has spent.
 * @see DepotReport#expenses
 * <p>
 * Total cost
 * @see DepotReport#totalProdCost
 * <p>
 * Total cost
 * @see DepotReport#totalDeliveryCost
 */
public class DepotReport {
    private int depotId;
    private int prodSold;
    private int prodBought;
    private int income;
    private int expenses;
    private int totalProdCost;
    private int totalDeliveryCost;

    public DepotReport(int depotId, int prodSold, int prodBought, int income, int totalProdCost, int totalDeliveryCost) {
        this.depotId = depotId;
        this.prodSold = prodSold;
        this.prodBought = prodBought;
        this.income = income;
        this.totalProdCost = totalProdCost;
        this.totalDeliveryCost = totalDeliveryCost;
        this.expenses = totalProdCost + totalDeliveryCost;
    }

    public int getDepotId() {
        return depotId;
    }

    public int getProdSold() {
        return prodSold;
    }

    public int getProdBought() {
        return prodBought;
    }

    public int getIncome() {
        return income;
    }

    public int getTotalProdCost() {
        return totalProdCost;
    }

    public int getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public int getExpenses() {
        return expenses;
    }

    @Override
    public String toString() {
        return "models.DepotReport{" +
                "depotId=" + depotId +
                ", prodSold=" + prodSold +
                ", prodBought=" + prodBought +
                ", income=" + income +
                ", totalProdCost=" + totalProdCost +
                ", totalDeliveryCost=" + totalDeliveryCost +
                ", expenses=" + expenses +
                '}';
    }
}
