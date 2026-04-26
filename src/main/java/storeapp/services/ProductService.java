package storeapp.services;

import storeapp.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product createProduct();
    public Product getProductById(int id);
    public Optional<Product> getProductByDescription(String description);
    public Product updateProduct(int id);
    public void deleteProduct(int id);
    public List<Product> getAllProducts();
    public boolean purchaseProduct(int productId, int quantity);
}
