package pl.palkaszymon.sales.productdetails;

import java.util.Optional;

public interface ProductDetailsProvider {
    Optional<ProductDetails> load(String productId);
}
