package models;

import purchasing.TicketCarer;

import java.util.List;

/**
 * This is a model for a company's record.
 * It contains financial details for a company based on transactions performed.
 */
public class CompanyRecord {

    private String companyName;
    private int income;
    private int expenses;
    private int cashflow;
    private List<Ticket> tickets;

    public CompanyRecord(String companyName, TicketCarer carer) {

        this.companyName = companyName;
        this.tickets = carer.getTicketList();

        calculateIncome();
        calculateExpenses();
        calculateCashflow();

    }

    /**
     * Determines how much money a company has made after all transactions have been carried out.
     *
     * @return company's income.
     */
    private int calculateIncome() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        int temp = tickets.stream().filter(ticket ->
                ticket.getSeller().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();

        return income = temp;
    }

    /**
     * Determines how much money a company has spent after all transactions have been carried out.
     *
     * @return company's expenses.
     */
    private int calculateExpenses() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        int temp = tickets.stream().filter(ticket ->
                ticket.getBuyer().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();

        return expenses = temp;
    }

    /**
     * Determines a company's cash flow after all transactions have been carried out.
     *
     * @return company's cash flow.
     */
    private int calculateCashflow() {
        return cashflow = getIncome() - getExpenses();
    }

    /**
     * @return the company's income.
     */
    public int getIncome() {
        return income;
    }

    /**
     * @return the company's expenses.
     */
    public int getExpenses() {
        return expenses;
    }

    /**
     * @return the company's cash flow.
     */
    public int getCashFlow() {
        return cashflow;
    }

    /**
     * @return the company's name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return a list of all tickets.
     */
    private List<Ticket> getTickets() {
        return tickets;
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
