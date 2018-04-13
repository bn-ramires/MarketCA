package models;

import purchasing.TicketCarer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private String companyName;
    private List<Ticket> tickets;
    private List<DepotReport> reports = new ArrayList<>();

    int prodSold;
    int income;
    int prodBought;
    int costProdductsBought;
    int totalDeliveryCost;


    public Report(String companyName, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();
    }


    public List<DepotReport> generateFullReport(int numberOfDepots) {

        // A list of tickets where a particular company was involved in the transaction
        List<Ticket> filtered = getTickets().stream()
                .filter(ticket -> ticket.getBuyer().equals(getCompanyName()) ||
                        ticket.getSeller().equals(getCompanyName()))
                .collect(Collectors.toList());

        for (int i = 0; i <= numberOfDepots; i++) {
            int current = i;

            setProdSold(filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum());

            setIncome(filtered.stream().filter(ticket -> current ==
                    ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum());

            setProdBought(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum());

            setCostProdductsBought(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum());

            setTotalDeliveryCost(filtered.stream().filter(ticket -> current ==
                    ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum());

            addReports(new DepotReport(i,
                    getProdSold(),
                    getProdBought(),
                    getIncome(),
                    getCostProdductsBought(),
                    getTotalDeliveryCost()));
        }
        return getReports();
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addReports(DepotReport report) {
        this.reports.add(report);
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

    public int getCostProdductsBought() {
        return costProdductsBought;
    }

    public void setCostProdductsBought(int costProdductsBought) {
        this.costProdductsBought = costProdductsBought;
    }

    public int getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public void setTotalDeliveryCost(int totalDeliveryCost) {
        this.totalDeliveryCost = totalDeliveryCost;
    }
}







