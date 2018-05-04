package models;

/**
 * A model for a ticket. This is a simple POJO that holds all information for each transaction performed.
 */
public class Ticket {

    String buyer;
    String seller;
    int buyerDepotId;
    int sellerDepotId;
    int productCost;
    int totalCost;
    int quantity;
    int deliveryCost;

    public Ticket(String buyer,
                  String seller,
                  int buyerDepotId,
                  int sellerDepotId,
                  int productCost,
                  int quantity,
                  int deliveryCost) {

        this.buyer = buyer;
        this.seller = seller;
        this.buyerDepotId = buyerDepotId;
        this.sellerDepotId = sellerDepotId;
        this.productCost = productCost;
        this.quantity = quantity;
        this.deliveryCost = deliveryCost;
        this.totalCost = quantity * productCost;

    }

    /**
     * @return The depot who made a purchase.
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * @return The depot who sold the product(s).
     */
    public String getSeller() {
        return seller;
    }

    /**
     * @return The total cost for all products bought/sold.
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * @return The ID number of the buyer depot.
     */
    public int getBuyerDepotId() {
        return buyerDepotId;
    }

    /**
     * @return The ID number of the seller depot.
     */
    public int getSellerDepotId() {
        return sellerDepotId;
    }

    /**
     * @return The quantity of products bought/sold.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return The seller's charge for delivery.
     */
    public int getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * @return The cost of the product bought/sold.
     */
    public int getProductCost() {
        return productCost;
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
                ", deliveryCost=" + deliveryCost +
                '}';
    }
}
