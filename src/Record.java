import java.util.List;

public class Record {


    private int income;
    private int expenses;
    private int cashflow;
    private String companyName;

    public Record(String companyName, List<Ticket> tickets) {
        this.companyName = companyName;
        this.income = calculateIncome(companyName, tickets);
        this.expenses = calculateExpenses(companyName, tickets);
        this.cashflow = calculateCashflow(income,expenses);
    }


    private int calculateIncome(String companyName, List<Ticket> tickets){
        return tickets.stream().filter(ticket -> ticket.getSeller().equals(companyName)).mapToInt(Ticket::getTotalCost).sum();

    }
    private int calculateExpenses(String companyName, List<Ticket> tickets){
        return tickets.stream().filter(ticket -> ticket.getBuyer().equals(companyName) ).mapToInt(Ticket::getTotalCost).sum();
    }
    private int calculateCashflow(int income, int expenses){
        return income - expenses;
    }

}
