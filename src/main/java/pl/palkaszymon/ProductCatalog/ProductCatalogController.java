package pl.palkaszymon.ProductCatalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductCatalogController {
    private ProductCatalog catalog;

    public ProductCatalogController(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/api/products")
    List<Product> allProducts(){
        return catalog.allPublishedProducts();
    }
}
