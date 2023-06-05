package pl.palkaszymon.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CollectingProductsTest {
    CartStorage cartStorage;
    AlwaysMissingProductDetailsProvider alwaysMissingProductDetailsProvider;
    @BeforeEach
    void setup(){
        cartStorage = new CartStorage();
        alwaysMissingProductDetailsProvider = new AlwaysMissingProductDetailsProvider();
    }

    @Test
    void itAllowsToAddProductsToCart(){
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct();

        sales.addToCart(customerId, productId);

        assertCustomerCartContainsNProducts(customerId, 1);

    }

    private void assertCustomerCartContainsNProducts(String customerId, int productCount) {

    }

    private String thereIsProduct() {
        return UUID.randomUUID().toString();
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, alwaysMissingProductDetailsProvider);
    }
}
