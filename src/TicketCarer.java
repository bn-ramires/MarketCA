import java.util.ArrayList;
import java.util.List;

public class TicketCarer {
    private List<Ticket> tickets = new ArrayList<>();

    public TicketCarer() { }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public Ticket getTicket(int index){
        return tickets.get(index);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
