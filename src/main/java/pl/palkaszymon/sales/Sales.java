package pl.palkaszymon.sales;

import pl.palkaszymon.sales.cart.Cart;
import pl.palkaszymon.sales.cart.CartStorage;
import pl.palkaszymon.sales.offer.Offer;
import pl.palkaszymon.sales.offer.OfferCalculator;
import pl.palkaszymon.sales.offer.EveryNItemLineDiscountPolicy;
import pl.palkaszymon.sales.offer.TotalDiscountPolicy;
import pl.palkaszymon.sales.productdetails.NoSuchProductException;
import pl.palkaszymon.sales.productdetails.ProductDetails;
import pl.palkaszymon.sales.productdetails.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetails, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetails;
        this.offerCalculator = offerCalculator;
    }

    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product.getId());

        cartStorage.addForUser(customerId, customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500), BigDecimal.valueOf(50)),
                new EveryNItemLineDiscountPolicy(5)
        );

        return offer;
    }

    public void acceptOffer() {

    }
}
