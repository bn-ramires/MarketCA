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
}
