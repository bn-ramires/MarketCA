package models;

import java.util.List;

/**
 * A model for a Depot Report. It contains detailed financial information for all transactions
 * in which this depot was involved in. Either as a buyer or a seller.
 */
public class DepotReport {
    private int depotId;
    private int qtyProductsBought;
    private int qtyProductsSold;
    private int income;
    private int expenses;
    private int cashAllowance;
    private int currentBalance;
    private int cashFlow;

    private List<Ticket> ticketsAsBuyer;
    private List<Ticket> ticketsAsSeller;

    public DepotReport(List<Ticket> ticketsAsBuyer,
                       List<Ticket> ticketsAsSeller,
                       int depotId,
                       int cashAllowance) {

        calcTotalProductBought(ticketsAsBuyer);
        calcTotalProductSold(ticketsAsSeller);

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
     * It returns the income of a depot after transactions are made.
     *
     * @param ticketsAsSeller ticket in which a particular depot is the seller.
     * @return depot's income.
     */
    public int calcIncome(List<Ticket> ticketsAsSeller) {
        return ticketsAsSeller.stream().mapToInt(Ticket::getTotalCost).sum();
    }

    /**
     * It returns the expenses of a depot after transactions are made.
     *
     * @param ticketsAsBuyer tickets in which a particular depot is the buyer.
     * @return depot's expenses.
     */
    public int calcExpenses(List<Ticket> ticketsAsBuyer) {
        int totalProductCost = ticketsAsBuyer.stream().mapToInt(Ticket::getTotalCost).sum();
        int totalDeliveryCost = ticketsAsBuyer.stream().mapToInt(Ticket::getDeliveryCost).sum();
        int totalExpenses = totalProductCost + totalDeliveryCost;
        return totalExpenses;
    }

    /**
     * It calculates the cash flow of a depot after transactions are made.
     *
     * @param income   the total income of a given depot.
     * @param expenses total expenses of a given depot.
     * @return depot's cash flow.
     */
    public int calcCashFlow(int income, int expenses) {
        return income - expenses;
    }

    /**
     * @return The cash flow of a depot after transactions are made.
     */
    public int getCashAllowance() {
        return cashAllowance;
    }

    /**
     * Calculates the quantity of products sold by a depot after transactions are made.
     *
     * @param ticketsAsSeller Tickets in which a given depot is the seller.
     */
    public void calcTotalProductSold(List<Ticket> ticketsAsSeller) {
        int result = ticketsAsSeller.stream().mapToInt(Ticket::getQuantity).sum();
        this.qtyProductsSold = result;
    }

    /**
     * Calculates the quantity of products bought by a depot after transactions are made.
     *
     * @param ticketsAsBuyer Tickets in which a given depot is the buyer.
     */
    public void calcTotalProductBought(List<Ticket> ticketsAsBuyer) {
        int result = ticketsAsBuyer.stream().mapToInt(Ticket::getQuantity).sum();
        this.qtyProductsBought = result;
    }

    /**
     * Calculates the current balance of a depot after transactions are made.
     *
     * @param cashAllowance input necessary for calculations.
     * @param cashFlow      input necessary for calculations.
     * @return the depot's current balance.
     */
    public int calcCurrentBalance(int cashAllowance, int cashFlow) {
        return cashAllowance + cashFlow;
    }

    /**
     * @return the depot's ID number.
     */
    public int getDepotId() {
        return depotId;
    }

    /**
     * @return the quantity of products sold by this depot.
     */
    public int getQtyProductsSold() {
        return qtyProductsSold;
    }

    /**
     * @return the quantity of products bought by this depot.
     */
    public int getQtyProductsBought() {
        return qtyProductsBought;
    }

    /**
     * @return the cash flow of this depot after transactions.
     */
    public int getCashFlow() {
        return cashFlow;
    }

    /**
     * @return the amount of money this depot has made.
     */
    public int getIncome() {
        return income;
    }

    /**
     * @return the amount of money this depot has spent.
     */
    public int getExpenses() {
        return expenses;
    }

    /**
     * @return the amount of money this depot currently has.
     */
    public int getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @return all transactions this depot was involved in as a buyer.
     */
    public List<Ticket> getTicketsAsBuyer() {
        return ticketsAsBuyer;
    }

    /**
     * @return all transactions this depot was involved in as a seller.
     */
    public List<Ticket> getTicketsAsSeller() {
        return ticketsAsSeller;
    }
}

