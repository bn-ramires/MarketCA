package purchasing;

import models.Ticket;

import java.util.ArrayList;

public class TicketCarer {
    private ArrayList<Ticket> ticketList = new ArrayList<>();

    public TicketCarer() { }

    public Ticket addTicket(Ticket ticket){
        ticketList.add(ticket);
        return ticket;
    }

    public Ticket getTicket(int index){
        return ticketList.get(index);
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }
}
