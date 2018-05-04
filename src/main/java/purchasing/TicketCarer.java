package purchasing;

import models.Ticket;

import java.util.ArrayList;

/**
 * The carer for tickets. This is part of the memento pattern.
 */
public class TicketCarer {

    private ArrayList<Ticket> ticketList = new ArrayList<>();

    public TicketCarer() { }

    /**
     * Adds a ticket to the ticket list.
     * @param ticket to be added.
     * @return the added ticket.
     */
    public Ticket addTicket(Ticket ticket){
        ticketList.add(ticket);
        return ticket;
    }

    /**
     * Returns a specific ticket from the ticket list.
     * @param index the position of the ticket to be returned.
     * @return the desired ticket object.
     */
    public Ticket getTicket(int index){
        return ticketList.get(index);
    }

    /**
     * @return The list containing all tickets.
     */
    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }
}
