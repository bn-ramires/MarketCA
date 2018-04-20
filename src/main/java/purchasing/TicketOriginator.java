package purchasing;

import models.Ticket;

public class TicketOriginator {

    private String buyer;
    private String seller;
    private int buyerDepotId;
    private int sellerDepotId;
    private int productCost;
//    private int totalCost;
    private int quantity;
    private int delivery;

    public Ticket saveTicketState() {
        return new Ticket(buyer, seller, buyerDepotId, sellerDepotId, productCost, quantity, delivery);
    }

    public void getTicketState(Ticket ticket) {
        this.buyer = ticket.getBuyer();
        this.seller = ticket.getSeller();
        this.buyerDepotId = ticket.getBuyerDepotId();
        this.sellerDepotId = ticket.getSellerDepotId();
        this.quantity = ticket.getQuantity();
        this.delivery = ticket.getDelivery();
//        this.totalCost = productCost * quantity;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getBuyerDepotId() {
        return buyerDepotId;
    }

    public void setBuyerDepotId(int buyerDepotId) {
        this.buyerDepotId = buyerDepotId;
    }

    public int getSellerDepotId() {
        return sellerDepotId;
    }

    public void setSellerDepotId(int sellerDepotId) {
        this.sellerDepotId = sellerDepotId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
        System.out.println(this.productCost);
        System.out.println(productCost);
    }

}
