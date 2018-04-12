import java.util.List;

public class Record {


    private int income;
    private int expenses;
    private int cashflow;
    private String companyName;
    private List<Ticket> tickets;

    public Record(String companyName, List<Ticket> tickets) {
        this.companyName = companyName;
        this.tickets = tickets;

        calculateIncome();
        calculateExpenses();
        calculateCashflow();
    }


    private void calculateIncome() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        income = tickets.stream().filter(ticket ->
                ticket.getSeller().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();
    }

    private void calculateExpenses() {

        String companyName = getCompanyName();
        List<Ticket> tickets = getTickets();

        expenses = tickets.stream().filter(ticket ->
                ticket.getBuyer().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();
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
}
