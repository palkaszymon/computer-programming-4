package pl.palkaszymon.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.palkaszymon.sales.cart.Cart;
import pl.palkaszymon.sales.cart.CartStorage;
import pl.palkaszymon.sales.offer.OfferCalculator;
import pl.palkaszymon.sales.productdetails.InMemoryProductDetailsProvider;
import pl.palkaszymon.sales.productdetails.ProductDetails;

import java.math.BigDecimal;
import java.util.UUID;

public class CollectingProductsTest {
    CartStorage cartStorage;
    private InMemoryProductDetailsProvider productDetails;
    @BeforeEach
    void setup(){
        cartStorage = new CartStorage();
        this.productDetails = new InMemoryProductDetailsProvider();
    }

    @Test
    void itAllowsToAddProduct() {
        //ARRANGE
        Sales sales = thereIsSalesModule();
        String product1 = thereIsProduct("Lego set", BigDecimal.valueOf(10.10));
        String customerId = thereIsCustomer("Kuba");

        //Act
        sales.addToCart(customerId, product1);

        //Assert
        assertThereIsXProductsInCustomerCart(1, customerId);
    }

    private void assertCustomerCartContainsNProducts(String customerId, int productCount) {

    }

    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        productDetails.add(new ProductDetails(id, name, price));

        return id;
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetails, new OfferCalculator(productDetails));
    }

    private void assertThereIsXProductsInCustomerCart(int totalProductsQuantity, String customerId) {
        Cart cart = cartStorage.load(customerId).get();

        assert cart.itemsCount() == totalProductsQuantity;
    }
}
