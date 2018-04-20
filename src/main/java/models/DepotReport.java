package models;

import java.util.List;

public class DepotReport {
    private int depotId;
    private int totalProductSold;
    private int totalProductBought;
    private int income;
    private int expenses;
    private int cashAllowance;
    private int cashFlow;
    private int currentBalance;
    String companyName;

    private List<Ticket> ticketsAsBuyer;
    private List<Ticket> ticketsAsSeller;

    public DepotReport(List<Ticket> ticketsAsBuyer,
                       List<Ticket> ticketsAsSeller,
                       int depotId,
                       int cashAllowance,
                       String companyName
    ) {
        setTotalProductBought(ticketsAsBuyer);
        setTotalProductSold(ticketsAsSeller);

        this.companyName = companyName;
        this.cashAllowance = cashAllowance;
        this.ticketsAsBuyer = ticketsAsBuyer;
        this.ticketsAsSeller = ticketsAsSeller;
        this.income = calcIncome(ticketsAsSeller);
        this.expenses = calcExpenses(ticketsAsBuyer);
        this.depotId = depotId;
        this.cashFlow = calcCashFlow(getIncome(), getExpenses());
        this.currentBalance = calcCurrentBalance(cashAllowance, getCashFlow());

    }

    /**
     * It returns the total income from a list of tickets.
     *
     * @param ticketsAsSeller ticket in which a particular depot is the seller.
     */
    public int calcIncome(List<Ticket> ticketsAsSeller) {
        return ticketsAsSeller.stream().mapToInt(Ticket::getTotalCost).sum();
    }

    /**
     * It returns total amount of money expent from a list of tickets
     *
     * @param ticketsAsBuyer tickets in which a particular depot is the buyer
     */
    public int calcExpenses(List<Ticket> ticketsAsBuyer) {
        int totalProductCost = ticketsAsBuyer.stream().mapToInt(Ticket::getTotalCost).sum();
        int totalDeliveryCost = ticketsAsBuyer.stream().mapToInt(Ticket::getDelivery).sum();
        int totalExpenses = totalProductCost + totalDeliveryCost;
        return totalExpenses;
    }

    /**
     * I returns cashflow
     *
     * @param income   the total income of a given depot
     * @param expenses total expences of a given depot
     */
    public int calcCashFlow(int income, int expenses) {
        return income - expenses;
    }

    public int getCashAllowance() {
        return cashAllowance;
    }

    /**
     * Sets the totalProductSold property with a value obtained from the ticketsAsSeller list
     *
     * @param ticketsAsSeller Tickets in which a given depot is the seller
     */
    public void setTotalProductSold(List<Ticket> ticketsAsSeller) {
        int result = ticketsAsSeller.stream().mapToInt(Ticket::getQuantity).sum();
        this.totalProductSold = result;
    }

    /**
     * Sets the totalProductBought property with a value obtained from the ticketsAsBuyer list
     *
     * @param ticketsAsBuyer Tickets in which a given depot is the buyer
     */
    public void setTotalProductBought(List<Ticket> ticketsAsBuyer) {
        int result = ticketsAsBuyer.stream().mapToInt(Ticket::getQuantity).sum();
        this.totalProductBought = result;
    }

    public int calcCurrentBalance(int cashAllowance, int cashFlow) {
        return cashAllowance + cashFlow;
    }

    public int getDepotId() {
        return depotId;
    }

    public int getTotalProductSold() {
        return totalProductSold;
    }

    public int getTotalProductBought() {
        return totalProductBought;
    }

    public int getCashFlow() {
        return cashFlow;
    }

    public int getIncome() {
        return income;
    }

    public int getExpenses() {
        return expenses;
    }

    public List<Ticket> getTicketsAsBuyer() {
        return ticketsAsBuyer;
    }

    public List<Ticket> getTicketsAsSeller() {
        return ticketsAsSeller;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}

