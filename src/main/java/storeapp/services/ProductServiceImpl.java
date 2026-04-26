package storeapp.services;

import storeapp.domain.Category;
import storeapp.domain.Product;
import storeapp.repository.ProductRepository;
import storeapp.utils.CustomerFormValidation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    Scanner sc = new Scanner(System.in);
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct() {

        Product product = new Product();

        String prompt = "Ingrese el id del producto";
        product.setIdProduct(CustomerFormValidation.validateInt(prompt));

        System.out.println("Ingrese la descripción del producto");
        String description = sc.nextLine();
        product.setDescription(description);

        System.out.println("Ingrese el precio del producto");
        double price = sc.nextDouble();
        product.setPrice(price);
        sc.nextLine();

        System.out.println("Ingrese el stock del producto");
        int stock = sc.nextInt();
        product.setStock(stock);
        sc.nextLine();

        System.out.println("Estado del Producto");
        product.setState(CustomerFormValidation.validateBoolean("Ingrese el estado (true/false)"));

        System.out.println("Ingrese el id de la categoría");
        int categoryId = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la descripción de la categoría");
        String categoryDesc = sc.nextLine();
        Category category = new Category(categoryId, categoryDesc, true);
        product.setCategory(category);

        return productRepository.saveProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Optional<Product> getProductByDescription(String description) {
        return Optional.empty();
    }

    @Override
    public Product updateProduct(int id) {

        System.out.println("Estoy en el service de producto");
        Product product = productRepository.findProductById(id);

        if(product != null && product.getIdProduct() == id){

            System.out.println("Actualizar 1. id 2. Descripción 3. Precio 4. Stock 5. Estado");
            int option = CustomerFormValidation.validateInt("Opcion");

            switch (option){
                case 1:
                    product.setIdProduct(CustomerFormValidation.validateInt("Actualizar id"));
                    break;
                case 2:
                    product.setDescription(CustomerFormValidation.validateString("Actualizar descripción"));
                    break;
                case 3:
                    product.setPrice(CustomerFormValidation.validateDouble("Actualizar precio"));
                    break;
                case 4:
                    product.setStock(CustomerFormValidation.validateInt("Actualizar stock"));
                    break;
                case 5:
                    product.setState(CustomerFormValidation.validateBoolean("Actualizar estado"));
                    break;
                default:
                    System.out.println("Seleccione una opción");
                    break;
            }

            productRepository.updateProduct(id);
        }else{
            System.out.println("Producto no encontrado");
        }

        return product;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public boolean purchaseProduct(int productId, int quantity) {
        Product product = productRepository.findProductById(productId);
        if (product != null && product.getStock() >= quantity && product.getState()) {
            product.setStock(product.getStock() - quantity);
            productRepository.updateProduct(productId);
            return true;
        }
        return false;
    }
}
