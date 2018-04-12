package models;

import purchasing.TicketCarer;

import java.util.List;

/**
 * This is a model for a company's record.
 * It contains financial details based on the transactions performed.
 *
 * @see CompanyRecord#companyName
 * @see CompanyRecord#income
 * @see CompanyRecord#expenses
 * @see CompanyRecord#cashflow
 * @see CompanyRecord#tickets
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
     * @return company's income
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
     * @return company's expenses
     */
    private int calculateExpenses() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        int temp = tickets.stream().filter(ticket ->
                ticket.getBuyer().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();

        return expenses = temp;
    }

    /**
     * Determines a company's cashflow after all transactions have been carried out.
     *
     * @return company's cashflow
     */
    private int calculateCashflow() {
        return cashflow = getIncome() - getExpenses();
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
