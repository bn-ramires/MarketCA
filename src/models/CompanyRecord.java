package models;

import purchasing.TicketCarer;

import java.util.List;

public class CompanyRecord {

    private int income;
    private int expenses;
    private int cashflow;
    private String companyName;
    private List<Ticket> tickets;

    public CompanyRecord(String companyName, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();

        calculateIncome();
        calculateExpenses();
        calculateCashflow();
    }


    private void calculateIncome() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        setIncome(tickets.stream().filter(ticket ->
                ticket.getSeller().equals(companyName)).mapToInt(Ticket::getTotalCost).sum());
    }

    private void calculateExpenses() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        setExpenses(tickets.stream().filter(ticket ->
                ticket.getBuyer().equals(companyName)).mapToInt(Ticket::getTotalCost).sum());
    }

    private void calculateCashflow() {
        cashflow = getIncome() - getExpenses();
    }

    public int getIncome() {
        return income;
    }

    public int getExpenses() {
        return expenses;
    }

    public int getCashflow() {
        return cashflow;
    }

    public String getCompanyName() {
        return companyName;
    }

    private List<Ticket> getTickets() {
        return tickets;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public void setCashflow(int cashflow) {
        this.cashflow = cashflow;
    }

    @Override
    public String toString() {
        return "models.CompanyRecord{" +
                "income=" + income +
                ", expenses=" + expenses +
                ", cashflow=" + cashflow +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
