package pl.palkaszymon.sales;

import java.util.Optional;

public interface ProductDetailsProvider {
    Optional<ProductDetails> load(String productId);
}
