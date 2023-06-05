package pl.palkaszymon.sales.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Cart {
    private Map<String, Integer> products;
    int itemsCount;
    public Cart() {
        this.products = new HashMap<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void add(String product) {
        if (!isInCart(product)) {
            products.put(product, 1);
        } else {
            addQuantity(product);
        }
    }

    private void addQuantity(String product) {
        products.put(product, products.get(product) + 1);
    }

    public int itemsCount() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    private boolean isInCart(String product) {
        return products.containsKey(product);
    }

    public List<CartItem> getCartItems() {
        return products
                .entrySet()
                .stream()
                .map(es -> new CartItem(es.getKey(), es.getValue()))
                .collect(Collectors.toList());
    }
}
