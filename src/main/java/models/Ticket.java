package models;

public class Ticket {

    String buyer;
    String seller;
    int buyerDepotId;
    int sellerDepotId;
    int productCost;
    int totalCost;
    int quantity;
    int delivery;

    public Ticket(String buyer,
                  String seller,
                  int buyerDepotId,
                  int sellerDepotId,
                  int productCost,
                  int quantity,
                  int delivery) {

        this.buyer = buyer;
        this.seller = seller;
        this.buyerDepotId = buyerDepotId;
        this.sellerDepotId = sellerDepotId;
        this.productCost = productCost;
        this.quantity = quantity;
        this.delivery = delivery;
        this.totalCost = quantity * productCost;

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

    public int getTotalCost() { return totalCost; }

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


    @Override
    public String toString() {
        return "models.Ticket{" +
                "buyer='" + buyer + '\'' +
                ", seller='" + seller + '\'' +
                ", buyerDepotId=" + buyerDepotId +
                ", sellerDepotId=" + sellerDepotId +
                ", productCost=" + productCost +
                ", totalCost=" + totalCost +
                ", quantity=" + quantity +
                ", delivery=" + delivery +
                '}';
    }
}
