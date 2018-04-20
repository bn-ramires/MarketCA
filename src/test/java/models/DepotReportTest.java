package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepotReportTest {

    private List<Ticket> ticketsAsBuyer = new ArrayList<>();
    private List<Ticket> ticketsAsSeller = new ArrayList<>();

    @Before
    public void populateTicketAsBuyer(){
        Ticket ticket1 = new Ticket("BigA","BigB",1,1,5,1,5);
        Ticket ticket2 = new Ticket("BigA","BigB",1,2,5,2,5);
        Ticket ticket3 = new Ticket("BigA","BigC",1,3,5,2,5);
        Ticket ticket4 = new Ticket("BigA","BigB",1,4,5,3,5);
        ticketsAsBuyer.add(ticket1);
        ticketsAsBuyer.add(ticket2);
        ticketsAsBuyer.add(ticket3);
        ticketsAsBuyer.add(ticket4);
    }
    @Before
    public void populateTicketAsSeller(){
        Ticket ticket1 = new Ticket("BigB","BigA",1,1,5,3,5);
        Ticket ticket2 = new Ticket("BigC","BigA",2,1,5,6,5);
        Ticket ticket3 = new Ticket("BigC","BigA",40,1,5,2,5);
        Ticket ticket4 = new Ticket("BigB","BigA",30,1,5,3,5);
        ticketsAsSeller.add(ticket1);
        ticketsAsSeller.add(ticket2);
        ticketsAsSeller.add(ticket3);
        ticketsAsSeller.add(ticket4);
    }


    @Test
    public void shouldReturnTheCorrectTotalIncomeDueToProductSold() {
        DepotReport depotReport = new DepotReport(ticketsAsBuyer,ticketsAsSeller,1, 75);
        int expected = 15 + 30 + 10 + 15;
        int actual = depotReport.calcIncome(ticketsAsSeller);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnTheCorrectTotalExpensesAmountDueToProductBoughtPlusDeliveryCost(){
        DepotReport depotReport = new DepotReport(ticketsAsBuyer,ticketsAsSeller,1, 75);
        int totalDelivery = 4 * 5;
        int totalProductCost = 5 + 10 + 10 + 15;
        int expected = totalProductCost + totalDelivery;
        int actual = depotReport.calcExpenses(ticketsAsBuyer);
        assertEquals(expected,actual);

    }
}