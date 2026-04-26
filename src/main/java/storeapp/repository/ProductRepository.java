package storeapp.repository;

import storeapp.domain.Category;
import storeapp.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop Dell", 1200000.00, 10, true, new Category(1, "Electrónica", true)),
            new Product(2, "Mouse Logitech", 50000.00, 25, true, new Category(1, "Electrónica", true))
    ));

    public Product saveProduct(Product product){
        products.add(product);
        return product;
    }

    public List<Product> findAllProducts(){
        for (Product product: products) {
            System.out.println(product.getIdProduct() + " " + product.getDescription() + " " + product.getPrice() + " " + product.getStock() + " " + product.isState());
        }
        return products;
    }

    public Product findProductById(int id){
        System.out.println("repositorio: " + id);
        try{
            for(Product product: products){
                if(product.getIdProduct() == id){
                    System.out.println(product.getIdProduct() + " " + product.getDescription() + " " + product.getPrice() + " " + product.getStock() + " " + product.isState());
                }
                return product;
            }
            return null;
        }catch (Exception e){
            System.out.println("Producto no encontrado");
            return null;
        }
    }

    public Product updateProduct(int id){
        for(Product product: products){
            if(id == product.getIdProduct()){
                return product;
            }
        }
        return null;
    }

    public void deleteProduct(int id){
        for(Product product: products){
            if(id == product.getIdProduct()){
                products.remove(id);
            }
        }
    }
}

