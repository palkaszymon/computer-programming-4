package pl.palkaszymon.ProductCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public String addProduct(String name, String desc) {
        Product newOne =  new Product(
                UUID.randomUUID(),
                name,
                desc
        );

        products.add(newOne);

        return newOne.getId();
    }
}