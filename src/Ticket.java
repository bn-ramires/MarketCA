public class Ticket {

    String buyer;
    String seller;
    int buyerDepotId;
    int sellerDepotId;
    int quantity;
    double delivery;

    public Ticket(String buyer, String seller, int buyerDepotId, int sellerDepotId, int quantity, double delivery) {
        this.buyer = buyer;
        this.seller = seller;
        this.buyerDepotId = buyerDepotId;
        this.sellerDepotId = sellerDepotId;
        this.quantity = quantity;
        this.delivery = delivery;
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

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }
}
