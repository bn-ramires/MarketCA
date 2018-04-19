package models;

import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a full financial report after transactions are made.
 * <p>
 * It contains a list of depot reports.
 *
 * @see Report#reportList
 * <p>
 * The method responsible for the full report generation.
 * @see Report#generateFullReport(int)
 */
public class Report {

    private String companyName;
    private List<Ticket> tickets;
    private List<DepotReport> reportList = new ArrayList<>();

    int prodSold;
    int income;
    int prodBought;
    int costProductsBought;
    int totalDeliveryCost;


    public Report(String companyName, int numberOfDepots, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();

        generateFullReport(numberOfDepots);
    }

    public List<Ticket> filterTickets(int depotId, String role) {

        if (role.equals("buyer")) {
            List<Ticket> ticketsAsBuyer = getTickets().stream()
                    .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) &&
                            ticket.getBuyerDepotId() == depotId)
                    .collect(Collectors.toList());

            return ticketsAsBuyer;
        } else {

            List<Ticket> ticketsAsSeller = getTickets().stream()
                    .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) &&
                            ticket.getBuyerDepotId() == depotId)
                    .collect(Collectors.toList());
            return ticketsAsSeller;
        }
    }


    public List<DepotReport> generateFullReport(int numberOfDepots) {

        for (int i = 0; i < numberOfDepots; i++) {
            int current = i;

            List<Ticket> filteredBuyer = filterTickets(i, "buyer");
            List<Ticket> filteredSeller = filterTickets(i, "seller");

            DepotReport newDepotReport = new DepotReport(filteredBuyer, filteredSeller, current);
            addReports(newDepotReport);


//            setProdSold(filtered.stream().filter(ticket -> current ==
//                    ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum());
//
//            setProdBought(filtered.stream().filter(ticket -> current ==
//                    ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum());
//
//            setIncome(filtered.stream().filter(ticket -> current ==
//                    ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum());
//
//            setCostProductsBought(filtered.stream().filter(ticket -> current ==
//                    ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum());
//
//            setTotalDeliveryCost(filtered.stream().filter(ticket -> current ==
//                    ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum());

        }
        return getReportList();
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<DepotReport> getReportList() {
        return reportList;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addReports(DepotReport report) {
        this.reportList.add(report);
    }

    public int getProdSold() {
        return prodSold;
    }

    public void setProdSold(int prodSold) {
        this.prodSold = prodSold;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getProdBought() {
        return prodBought;
    }

    public void setProdBought(int prodBought) {
        this.prodBought = prodBought;
    }

    public int getCostProductsBought() {
        return costProductsBought;
    }

    public void setCostProductsBought(int costProductsBought) {
        this.costProductsBought = costProductsBought;
    }

    public int getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public void setTotalDeliveryCost(int totalDeliveryCost) {
        this.totalDeliveryCost = totalDeliveryCost;
    }

    public void setReportList(List<DepotReport> reportList) {
        this.reportList = reportList;
    }
}







