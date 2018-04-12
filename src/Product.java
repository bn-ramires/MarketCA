import java.util.Objects;

public class Product {

    String brand;
    int price;

    public Product(ProductBuilder builder) {
        this.brand = builder.input.name;
        this.price = builder.input.productCost;
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

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {

        return Objects.hash(brand, price);
    }
}
