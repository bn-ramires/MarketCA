import java.util.ArrayList;
import java.util.List;

public class TicketCarer {
    private List<Ticket> ticketList = new ArrayList<>();

    public TicketCarer() { }

    public void addTicket(Ticket ticket){
        ticketList.add(ticket);
    }

    public Ticket getTicket(int index){
        return ticketList.get(index);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
