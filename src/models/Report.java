package models;

import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private String companyName;
    private List<Ticket> tickets;
    private List<DepotReport> reports = new ArrayList<>();


    public Report(String companyName, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();
    }


    public List<DepotReport> generateFullReport() {
        List<Ticket> filtered = getTickets().stream()
                .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) ||
                        ticket.getSeller().equals(getCompanyName()))
                .collect(Collectors.toList());
        for(int i = 0; i<= 100; i++){
            int current = i;
            int prodSold = filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int income = filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int prodBought = filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int totalProdCost = filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int totalDelivery = filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum();

            reports.add(new DepotReport(i, prodSold, prodBought, income, totalProdCost, totalDelivery));
        }
       return reports;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<DepotReport> getReports() {
        return reports;
    }
}







