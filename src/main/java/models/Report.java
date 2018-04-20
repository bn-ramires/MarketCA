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

    /**
     * Returns only the tickets in which the current depot is either the buyer or the seller
     * @param depotId  Id of the depot being audited
     * @param role     specifies whether the current depot is the buyer or the seller
     * @param tickets  List of tickets containing only tickets in which the selected company is involved
     */

    public List<Ticket> filterTickets(int depotId, String role, List<Ticket> tickets) {

        if (role.equals("buyer")) {
            List<Ticket> ticketsAsBuyer = tickets.stream()
                    .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) &&
                            ticket.getBuyerDepotId() == depotId)
                    .collect(Collectors.toList());

            return ticketsAsBuyer;
        } else {

            List<Ticket> ticketsAsSeller = tickets.stream()
                    .filter(ticket -> ticket.getSeller().equals(getCompanyName()) &&
                            ticket.getSellerDepotId() == depotId)
                    .collect(Collectors.toList());
            return ticketsAsSeller;
        }
    }

    /**
     * It returns a list of DepotReports
     * @param numberOfDepots the number of depots for which depotRepots should be generated
     */

    public List<DepotReport> generateFullReport(int numberOfDepots) {

        for (int i = 0; i < numberOfDepots; i++) {
            int current = i;

            List<Ticket> filteredBuyer = filterTickets(i, "buyer", getTickets());
            List<Ticket> filteredSeller = filterTickets(i, "seller", getTickets());

            DepotReport newDepotReport = new DepotReport(filteredBuyer, filteredSeller, current);
            addReports(newDepotReport);

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

    /**
     * It adds a report to the reportList
     * @param report DeportReport object of a given depot
     */
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







