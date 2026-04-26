package storeapp.view;

import storeapp.services.ProductService;
import storeapp.utils.CustomerFormValidation;

public class ProductView {

    private final ProductService productService;

    public ProductView(ProductService productService){
        this.productService = productService;
    }

    public void createProduct(){
        productService.createProduct();
    }

    public void getProductById(int id){
        productService.getProductById(id);
    }

    public void updateProduct(){
        System.out.println("Estoy en la Vista de Producto");
        productService.updateProduct(CustomerFormValidation.validateInt("Ingrese el ID"));
    }

    public void deleteProduct(){
        productService.deleteProduct(CustomerFormValidation.validateInt("Ingrese el id del Producto a eliminar"));
    }

    public void getAllProducts(){
        productService.getAllProducts();
    }

    public void purchaseProduct() {
        int productId = CustomerFormValidation.validateInt("Ingrese el ID del producto a comprar");
        int quantity = CustomerFormValidation.validateInt("Ingrese la cantidad");
        boolean success = productService.purchaseProduct(productId, quantity);
        if (success) {
            System.out.println("Compra realizada exitosamente");
        } else {
            System.out.println("No se pudo realizar la compra. Verifique stock o estado del producto");
        }
    }
}
