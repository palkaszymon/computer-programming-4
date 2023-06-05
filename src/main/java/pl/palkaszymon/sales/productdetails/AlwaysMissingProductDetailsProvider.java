package pl.palkaszymon.sales.productdetails;

import pl.palkaszymon.sales.productdetails.ProductDetails;
import pl.palkaszymon.sales.productdetails.ProductDetailsProvider;

import java.util.Optional;

public class AlwaysMissingProductDetailsProvider implements ProductDetailsProvider {
    @Override
    public Optional<ProductDetails> load(String productId) {
        return Optional.empty();
    }
}