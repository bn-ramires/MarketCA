package models;

import main.Input;
import main.Market;

import java.util.List;

public class DepotReport {
    private int depotId;
    private int totalProductSold;
    private int totalProductBought;
    //    private int soldTo;
    //    private int boughtFrom;
    private int income;
    private int expenses;
    //    private int totalProdCost;
    //    private int totalDeliveryCost;
    private int cashAllowance;
    private int cashFlow;


    private List<Ticket> ticketsAsBuyer;
    private List<Ticket> ticketsAsSeller;

    public DepotReport(List<Ticket> ticketsAsBuyer, List<Ticket> ticketsAsSeller, int depotId) {
        setTotalProductBought(ticketsAsBuyer);
        setTotalProductSold(ticketsAsSeller);
        calcCashAllowance();

        this.ticketsAsBuyer = ticketsAsBuyer;
        this.ticketsAsSeller = ticketsAsSeller;
        this.income = calcIncome(ticketsAsSeller);
        this.expenses = calcExpenses(ticketsAsBuyer);
        this.cashFlow = calcCashFlow(income, expenses);
        this.depotId = depotId;


    }

    public int calcIncome(List<Ticket> ticketsAsSeller) {
        return ticketsAsSeller.stream().mapToInt(Ticket::getTotalCost).sum();
    }

    public int calcExpenses(List<Ticket> ticketsAsBuyer) {
        int totalDeliveryCost = ticketsAsBuyer.stream().mapToInt(Ticket::getDelivery).sum();
        int totalExpenses = getTotalProductBought() + totalDeliveryCost;
        return totalExpenses;
    }

    public int calcCashFlow(int income, int expenses) {
        return income - expenses;
    }

    public int getCashAllowance() {
        return cashAllowance;
    }

    public void calcCashAllowance() {
        this.cashAllowance = Input.getCashAllowance();
    }

    public void setTotalProductSold(List<Ticket> ticketsAsSeller) {
        int result = ticketsAsSeller.stream().mapToInt(Ticket::getTotalCost).sum();
        this.totalProductSold = result;
    }

    public void setTotalProductBought(List<Ticket> ticketsAsBuyer) {
        int result = ticketsAsBuyer.stream().mapToInt(Ticket::getTotalCost).sum();
        this.totalProductBought = result;
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
}

