import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {

    private String companyName;
    private List<Ticket> tickets;


    public Report(String companyName, TicketCarer carer) {
        this.companyName = companyName;
        this.tickets = carer.getTicketList();
    }


    public void generateReport() {
        List<Ticket> filtered = tickets.stream()
                .filter(ticket -> ticket.getBuyer().equals(companyName) ||
                        ticket.getSeller().equals(companyName))
                .collect(Collectors.toList());
        for(int i = 0; i<= 100; i++){
            int finalI = i;
            int bought = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int totalProdCost = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int sold = filtered.stream().filter(ticket -> finalI == ticket.getSellerDepotId()).mapToInt(Ticket::getQuantity).sum();
            int income = filtered.stream().filter(ticket -> finalI == ticket.getSellerDepotId()).mapToInt(Ticket::getTotalCost).sum();
            int tolatDelivery = filtered.stream().filter(ticket -> finalI == ticket.getBuyerDepotId()).mapToInt(Ticket::getDelivery).sum();
            System.out.println("---------------------------------");
            System.out.println("Depot Id: " + i);
            System.out.println("Prod sold: " + sold+ " Total: $"+ income);
            System.out.println("Prod bought " + bought + " Total: $"+ totalProdCost);
        }


    }


}
