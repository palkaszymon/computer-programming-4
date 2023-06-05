package pl.palkaszymon.sales.productdetails;

import pl.palkaszymon.ProductCatalog.Product;
import pl.palkaszymon.ProductCatalog.ProductCatalog;
import pl.palkaszymon.sales.productdetails.ProductDetails;
import pl.palkaszymon.sales.productdetails.ProductDetailsProvider;

import java.util.Optional;

public class ProductCatalogProductDetailsProvider implements ProductDetailsProvider {
    ProductCatalog productCatalog;

    public ProductCatalogProductDetailsProvider(ProductCatalog productCatalog){
        this.productCatalog = productCatalog;
    }
    @Override
    public Optional<ProductDetails> load(String productId) {
        Product product = productCatalog.loadById(productId);
        if (product == null){
            return Optional.empty();
        }
        return Optional.of(new ProductDetails(product.getId(), product.getName(), product.getPrice()));
    }
}
