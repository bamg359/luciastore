package storeapp.services;

import storeapp.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    Product updateProduct(Product product);
    boolean deleteProduct(int id);
}
