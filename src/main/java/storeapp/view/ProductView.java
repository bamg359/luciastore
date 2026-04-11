package storeapp.view;

import storeapp.domain.Category;
import storeapp.domain.Product;
import storeapp.services.ProductService;
import storeapp.services.StateSelector;

import java.util.Scanner;

public class ProductView {

    Scanner sc = new Scanner(System.in);
    private final ProductService productService;

    public ProductView(ProductService productService) {
        this.productService = productService;
    }

    public void createProduct() {
        Product product = productService.createProduct(new Product());
        System.out.println("Producto creado: " + product.getIdProduct() + " - " + product.getDescription());
    }

    public void getAllProducts() {
        System.out.println("--- Listado de Productos ---");
        productService.getAllProducts().forEach(p ->
                System.out.println(p.getIdProduct() + " | " + p.getDescription() +
                        " | Precio: " + p.getPrice() +
                        " | Stock: " + p.getStock() +
                        " | Estado: " + p.isState() +
                        " | Categoria: " + (p.getCategory() != null ? p.getCategory().getDescription() : "Sin categoria"))
        );
    }

    public void getProductById() {
        System.out.println("Ingrese el id del producto:");
        int id = sc.nextInt();
        sc.nextLine();
        productService.getProductById(id)
                .ifPresentOrElse(
                        p -> System.out.println(p.getIdProduct() + " | " + p.getDescription() +
                                " | Precio: " + p.getPrice() +
                                " | Stock: " + p.getStock()),
                        () -> System.out.println("Producto no encontrado")
                );
    }

    public void updateProduct() {
        System.out.println("Ingrese el id del producto a modificar:");
        int id = sc.nextInt();
        sc.nextLine();
        productService.getProductById(id)
                .ifPresentOrElse(
                        p -> {
                            productService.updateProduct(p);
                            System.out.println("Producto actualizado correctamente");
                        },
                        () -> System.out.println("Producto no encontrado")
                );
    }

    public void deleteProduct() {
        System.out.println("Ingrese el id del producto a eliminar:");
        int id = sc.nextInt();
        sc.nextLine();
        boolean deleted = productService.deleteProduct(id);
        System.out.println(deleted ? "Producto eliminado correctamente" : "Producto no encontrado");
    }
}
