package storeapp.config;

import storeapp.domain.Admin;
import storeapp.repository.CustomerRepository;
import storeapp.repository.ProductRepository;
import storeapp.repository.CategoryRepository;
import storeapp.services.*;
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

        // 2. ✅ AuthService — usa el mismo customerRepository
        AuthService authService = new AuthService(customerRepository);

        // 3. Configuración de Categorías
        CategoryRepository categoryRepository = new CategoryRepository();
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        CategoryView categoryView = new CategoryView(categoryService);

        // 4. Configuración de Productos
        ProductRepository productRepository = new ProductRepository();
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryRepository);
        ProductView productView = new ProductView(productService);

        // 5. Retorno con las 4 vistas + authService
        return new MenuApp(customerView, adminView, productView, categoryView, authService);
    }
}