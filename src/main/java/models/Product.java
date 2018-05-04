package models;

/**
 * This is a model for a product and it holds all necessary information about it.
 */
public class Product {

    String brand;
    public int price;

    public Product(ProductBuilder builder) {
        this.brand = builder.input.name;
        this.price = builder.input.productCost;
    }

    public Product() {
    }

    /**
     * @return the company that has made this product.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the cost of this product.
     */
    public int getPrice() {
        return price;
    }

    /**
     * The builder pattern for the creation of products.
     */
    public static class ProductBuilder {

        Company.CompanyBuilder input;

        public ProductBuilder(Company.CompanyBuilder input) {
            this.input = input;
        }

        /**
         * @return builds a new Product object.
         */
        public Product build() {
            return new Product(this);
        }
    }

    /**
     * @param brand the company that has made this product.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @param price the cost of this product.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "models.Product{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
