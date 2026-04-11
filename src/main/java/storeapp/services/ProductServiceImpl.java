package storeapp.services;


import storeapp.domain.Product;
import storeapp.repository.CategoryRepository;
import storeapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    Scanner sc = new Scanner(System.in);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StateSelector stateSelector = new StateSelector();

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        System.out.println("Ingrese el id del producto:");
        int id = sc.nextInt();
        sc.nextLine();
        product.setIdProduct(id);

        System.out.println("Ingrese la descripcion del producto:");
        String description = sc.nextLine();
        product.setDescription(description);

        System.out.println("Ingrese el precio del producto:");
        double price = sc.nextDouble();
        product.setPrice(price);

        System.out.println("Ingrese el stock del producto:");
        int stock = sc.nextInt();
        sc.nextLine();
        product.setStock(stock);

        boolean state = stateSelector.ProductState();
        product.setState(state);

        System.out.println("--- Categorias disponibles ---");
        categoryRepository.findAllCategories().forEach(c ->
                System.out.println(c.getIdCategory() + " | " + c.getDescription())
        );
        System.out.println("Ingrese el id de la categoria:");
        int categoryId = sc.nextInt();
        sc.nextLine();
        categoryRepository.findById(categoryId)
                .ifPresentOrElse(
                        product::setCategory,
                        () -> System.out.println("Categoria no encontrada, se asignara sin categoria")
                );

        return productRepository.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        System.out.println("Ingrese la nueva descripcion:");
        String description = sc.nextLine();
        product.setDescription(description);

        System.out.println("Ingrese el nuevo precio:");
        double price = sc.nextDouble();
        product.setPrice(price);

        System.out.println("Ingrese el nuevo stock:");
        int stock = sc.nextInt();
        sc.nextLine();
        product.setStock(stock);

        boolean state = stateSelector.ProductState();
        product.setState(state);

        return productRepository.updateProduct(product)
                .orElse(null);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productRepository.deleteById(id);
    }
}
