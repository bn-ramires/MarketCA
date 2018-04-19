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


    public List<DepotReport> generateFullReport(int numberOfDepots) {

        // A list of tickets where a particular company was involved in the transaction
        List<Ticket> filtered = getTickets().stream()
                .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) ||
                        ticket.getSeller().equals(getCompanyName()))
                .collect(Collectors.toList());

        for (int i = 0; i < numberOfDepots; i++) {
            int current = i;

            setProdSold(filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum());

            setProdBought(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum());

            setIncome(filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum());

            setCostProductsBought(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum());

            setTotalDeliveryCost(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum());

            addReports(new DepotReport(i,
                    getProdSold(),
                    getProdBought(),
                    getIncome(),
                    getCostProductsBought(),
                    getTotalDeliveryCost(), 100));
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
}







