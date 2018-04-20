package models;

/**
 * This is a model for a product and it holds all necessary information about it.
 * <p>
 * The company that has made this product.
 *
 * @see Product#brand
 * <p>
 * The cost of this product.
 * @see Product#price
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

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public static class ProductBuilder {

        Company.CompanyBuilder input;

        public ProductBuilder(Company.CompanyBuilder input) {
            this.input = input;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

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
