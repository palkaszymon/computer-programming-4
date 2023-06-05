
package pl.palkaszymon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.palkaszymon.ProductCatalog.ProductCatalog;
import pl.palkaszymon.ProductCatalog.HashMapProductStorage;
import pl.palkaszymon.sales.cart.CartStorage;
import pl.palkaszymon.sales.Sales;
import pl.palkaszymon.sales.offer.OfferCalculator;
import pl.palkaszymon.sales.productdetails.InMemoryProductDetailsProvider;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createNewProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());

        String productId1 = catalog.addProduct("book", "nice book");
        catalog.assignImage(productId1, "images/nice.jpeg");
        catalog.changePrice(productId1, BigDecimal.TEN);
        catalog.publishProduct(productId1);

        String productId2 = catalog.addProduct("My book", "My nice book");
        catalog.assignImage(productId2, "images/nice.jpeg");
        catalog.changePrice(productId2, BigDecimal.valueOf(20.49));
        catalog.publishProduct(productId2);

        return catalog;
    }
    @Bean
    Sales createSales(ProductCatalog catalog) {
        InMemoryProductDetailsProvider productDetails = new InMemoryProductDetailsProvider();
        return new Sales(new CartStorage(), productDetails, new OfferCalculator(productDetails));
    }
}