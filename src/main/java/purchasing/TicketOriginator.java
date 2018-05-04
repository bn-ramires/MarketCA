package purchasing;

import models.Ticket;

/**
 * The originator for the ticket objects. This is part of the memento pattern.
 */
public class TicketOriginator {

    private String buyer;
    private String seller;
    private int buyerDepotId;
    private int sellerDepotId;
    private int productCost;
    private int quantity;
    private int deliveryCost;

    /**
     * It creates a Ticket object and returns it, properly populated.
     *
     * @return the saved ticket.
     */
    public Ticket saveTicketState() {
        return new Ticket(buyer, seller, buyerDepotId, sellerDepotId, productCost, quantity, deliveryCost);
    }

    /**
     * @param buyer The depot who made a purchase.
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * @param seller The depot who sold the product(s).
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }

    /**
     * @param buyerDepotId The ID number of the buyer depot.
     */
    public void setBuyerDepotId(int buyerDepotId) {
        this.buyerDepotId = buyerDepotId;
    }

    /**
     * @param sellerDepotId The ID number of the seller depot.
     */
    public void setSellerDepotId(int sellerDepotId) {
        this.sellerDepotId = sellerDepotId;
    }

    /**
     * @param quantity The quantity of products bought/sold.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @param deliveryCost The seller's charge for delivery.
     */
    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * @param productCost The cost of the product bought/sold.
     */
    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }

}
