package pl.palkaszymon.sales;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    private List<ProductDetails> products;
    int itemsCount;
    public Cart() {
        this.products = new ArrayList<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails product) {products.add(product);
    }

    public int itemsCount() {
        return products.size();
    }

    public List<ProductDetails> getProducts() {
        return products;
    }
}