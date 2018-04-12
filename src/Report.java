import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {

    private String companyName;
    private List<Ticket> tickets;
    private List<DepotReport> reports = new ArrayList<>();


    public Report(String companyName, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();
    }


    public List<DepotReport> generateFullReport() {
        List<Ticket> filtered = tickets.stream()
                .filter(ticket -> ticket.getBuyer().equals(companyName) ||
                        ticket.getSeller().equals(companyName))
                .collect(Collectors.toList());
        for(int i = 0; i<= 100; i++){
            int finalI = i;
            int prodSold = filtered.stream().filter(ticket -> finalI == ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int income = filtered.stream().filter(ticket -> finalI == ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int prodBought = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int totalProdCost = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int totalDelivery = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum();


            reports.add(new DepotReport(i, prodSold, prodBought, income, totalProdCost, totalDelivery));
        }
       return reports;
    }


}







