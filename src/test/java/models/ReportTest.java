package models;

import org.junit.Before;
import org.junit.Test;
import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ReportTest {

    List<Ticket> fakeTickets = new ArrayList<>();
    List<Depot> fakeDepots = new ArrayList<>();


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
    public void should_only_return_tickets_where_the_selected_comany_is_a_buyer() {
        Report report = new Report("BigA",fakeDepots, new TicketCarer());


        List<Ticket> actual = report.filterTickets(1,"buyer", getFakeTickets());


        assertEquals(2, actual.size());
        assertThat(actual.get(0), samePropertyValuesAs(fakeTickets.get(0)));
        assertThat(actual.get(1), samePropertyValuesAs(fakeTickets.get(6)));
    }

    @Test
    public void should_only_return_tickets_where_the_selected_comany_is_a_seller() {
        Report report = new Report("BigA",fakeDepots, new TicketCarer());


        List<Ticket> actual = report.filterTickets(5,"seller", getFakeTickets());


        assertEquals(1, actual.size());
        assertThat(actual.get(0), samePropertyValuesAs(fakeTickets.get(5)));

    }

    public List<Ticket> getFakeTickets() {
        return fakeTickets;
    }
}