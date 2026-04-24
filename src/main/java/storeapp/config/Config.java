package storeapp.config;

import storeapp.domain.Admin;
import storeapp.repository.CustomerRepository;
import storeapp.repository.ProductRepository;
import storeapp.repository.CategoryRepository;
import storeapp.services.AdminServiceImpl;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.services.ProductServiceImpl;
import storeapp.services.CategoryServiceImpl;
import storeapp.userinterface.MenuApp;
import storeapp.view.AdminView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;
import storeapp.view.CategoryView;

public class Config {

    public static MenuApp createMenuApp(){
        // 1. Configuración de Usuarios (Clientes y Admins)
        Admin admin = new Admin();
        CustomerRepository customerRepository = new CustomerRepository();
        CustumerService customerService = new CustumerServiceImpl(customerRepository);
        CustomerView customerView = new CustomerView(customerService);
        AdminServiceImpl adminService = new AdminServiceImpl(admin, customerRepository);
        AdminView adminView = new AdminView(adminService, admin);

        // 2. NUEVA SECCIÓN: Configuración de Categorías
        // (La movemos arriba porque ProductServiceImpl la necesita ahora)
        CategoryRepository categoryRepository = new CategoryRepository();
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        CategoryView categoryView = new CategoryView(categoryService);

        // 3. Configuración de Productos
        ProductRepository productRepository = new ProductRepository();
        // CORRECCIÓN: Ahora pasamos productRepository Y categoryRepository
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryRepository);
        ProductView productView = new ProductView(productService);

        // 4. RETORNO: Pasamos las 4 vistas al menú
        return new MenuApp(customerView, adminView, productView, categoryView);
    }
}
