package models;

import org.junit.Before;
import org.junit.Test;
import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportTest {

    List<Ticket> fakeTickets = new ArrayList<>();


    @Before
    public void populateFakeTickets(){
        Ticket ticket1 = new Ticket("BigA","BigB",1,1,5,3,5);
        Ticket ticket2 = new Ticket("BigA","BigB",2,2,5,5,5);
        Ticket ticket3 = new Ticket("BigA","BigC",60,1,5,2,5);
        Ticket ticket4 = new Ticket("BigC","BigB",2,6,5,3,5);
        Ticket ticket5 = new Ticket("BigB","BigC",70,50,5,2,5);
        Ticket ticket6 = new Ticket("BigC","BigA",5,5,5,4,5);
        Ticket ticket7 = new Ticket("BigA","BigB",1,2,5,2,5);

        fakeTickets.add(ticket1);
        fakeTickets.add(ticket2);
        fakeTickets.add(ticket3);
        fakeTickets.add(ticket4);
        fakeTickets.add(ticket5);
        fakeTickets.add(ticket6);
        fakeTickets.add(ticket7);
    }

    @Test
    public void should_only_return_tickets_where_the_selected_comany_is_either_seller_or_buyer() {
        Report report = new Report("BigA",100, new TicketCarer());


        List<Ticket> actual = report.filterTickets(1,"buyer", getFakeTickets());

        assertEquals(2, actual.size());
        assertEquals(fakeTickets.get(0),actual.get(0));
        assertEquals(fakeTickets.get(6),actual.get(1));
    }

    public List<Ticket> getFakeTickets() {
        return fakeTickets;
    }
}