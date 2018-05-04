package models;

import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a full financial report after transactions are made.
 */
public class Report {

    private String companyName;
    private List<Ticket> tickets;
    private List<DepotReport> depotReportList = new ArrayList<>();

    public Report(String companyName, List<Depot> depots, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();

        generateFullReport(depots);
    }

    /**
     * Returns only the tickets in which the current depot is either the buyer or the seller.
     *
     * @param depotId  Id of the depot being audited.
     * @param role     specifies whether the current depot is the buyer or the seller.
     * @param tickets  List of tickets containing only tickets in which the selected company is involved.
     *
     * @return a filtered list with the desired tickets.
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
     * It generates a financial report for each Depot of a selected company
     * @param depots List of Depot of the selected company
     * @return the depot report.
     */
    public List<DepotReport> generateFullReport(List<Depot> depots) {

        for (int i = 0; i < depots.size(); i++) {

            int depotId = i;

            // Filtering the list of tickets to grab only relevant ones.
            List<Ticket> filteredBuyer = filterTickets(i, "buyer", getTickets());
            List<Ticket> filteredSeller = filterTickets(i, "seller", getTickets());

            // Grabbing the depot's cash allowance.
            int cashAllowance = depots.get(i).getCashAllowance();

            // Initializing a new DepotReport object.
            DepotReport newDepotReport = new DepotReport(
                    filteredBuyer,
                    filteredSeller,
                    depotId,
                    cashAllowance);

            // Adding the new DepotReport to the list.
            addReports(newDepotReport);

        }
        return getDepotReportList();
    }

    /**
     * @return the name of the company that generated this report.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return a list containing all tickets for any transaction made.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * @return a list containing all depot reports of a company.
     */
    public List<DepotReport> getDepotReportList() {
        return depotReportList;
    }

    /**
     * @param report a depot report to be added to the depot report list.
     */
    public void addReports(DepotReport report) {
        this.depotReportList.add(report);
    }
}







