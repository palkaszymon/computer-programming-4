package pl.palkaszymon.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.palkaszymon.ProductCatalog.Product;
import pl.palkaszymon.ProductCatalog.ProductCatalog;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferAcceptanceHttpTest {
    @Autowired
    ProductCatalog catalog;
    @Autowired
    TestRestTemplate http;
    @Test
    void itAllowsToAcceptTheOffer() {
        // Arrange
        String productId = thereIsExampleProduct();
        //addToCart
        http.postForEntity(String.format("/api/add-to-cart/%s)", productId), null, Object.class);
        http.postForEntity(String.format("/api/add-to-cart/%s)", productId), null, Object.class);
        AcceptOfferRequest request = new AcceptOfferRequest("Szymon", "szymon@example.com");
        ResponseEntity<ReservationData> response = http.postForEntity(String.format("/api/accept-offer)"), request, ReservationData.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody().getPaymentId());
        assertNotNull(response.getBody().getPaymentUrl());
    }

    private String thereIsExampleProduct() {
        return catalog.allPublishedProducts()
                .stream()
                .findFirst()
                .map(Product::getId)
                .get();
    }


}
