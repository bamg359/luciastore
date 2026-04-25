package storeapp.config;

import storeapp.repository.AdminRepository;
import storeapp.repository.CategoryRepository;
import storeapp.repository.CustomerRepository;
import storeapp.repository.ProductRepository;
import storeapp.repository.CategoryRepository;
import storeapp.services.*;
import storeapp.services.AdminServiceImpl;
import storeapp.services.AuthContext;
import storeapp.services.CategoryServiceImpl;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.services.ProductServiceImpl;
import storeapp.userinterface.MenuApp;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;

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
    private Config() {
    }

    public static MenuApp createMenuApp() {
        // Repositorios
        CustomerRepository customerRepository = new CustomerRepository();
        AdminRepository adminRepository = new AdminRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        ProductRepository productRepository = new ProductRepository();

        // Servicios
        CustumerService customerService = new CustumerServiceImpl(customerRepository);
        AuthContext authContext = new AuthContext();
        AdminServiceImpl adminService = new AdminServiceImpl(customerRepository, adminRepository, authContext);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryRepository);

        // Vistas
        CustomerView customerView = new CustomerView(customerService);
        AdminView adminView = new AdminView(adminService);
        CategoryView categoryView = new CategoryView(categoryService);
        ProductView productView = new ProductView(productService);

        // 5. Retorno con las 4 vistas + authService
        return new MenuApp(customerView, adminView, productView, categoryView, authService);
    }
}
        // MenuApp con todas las dependencias necesarias
        return new MenuApp(customerView, adminView, productView, categoryView, adminService, adminRepository);
    }
}


