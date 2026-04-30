package storeapp.config;

import storeapp.repository.AdminRepository;
import storeapp.repository.CategoryRepository;
import storeapp.repository.CustomerRepository;
import storeapp.repository.OrderRepository;
import storeapp.repository.ProductRepository;
import storeapp.services.AdminServiceImpl;
import storeapp.services.AuthContext;
import storeapp.services.AuthService;
import storeapp.services.CategoryServiceImpl;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.services.OrderService;
import storeapp.services.OrderServiceImpl;
import storeapp.services.ProductServiceImpl;
import storeapp.userinterface.MenuApp;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.OrderView;
import storeapp.view.ProductView;

public class Config {

    private Config() {
    }

    public static MenuApp createMenuApp() {
        // Repositorios
        CustomerRepository customerRepository = new CustomerRepository();
        AdminRepository adminRepository = new AdminRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        // Contexto de sesión compartido entre servicios
        AuthContext authContext = new AuthContext();

        // Servicios
        CustumerService customerService = new CustumerServiceImpl(customerRepository);
        AdminServiceImpl adminService = new AdminServiceImpl(customerRepository, adminRepository, authContext);
        AuthService authService = new AuthService(adminRepository, customerRepository, authContext);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository, productRepository);

        // Vistas
        CustomerView customerView = new CustomerView(customerService);
        AdminView adminView = new AdminView(adminService);
        CategoryView categoryView = new CategoryView(categoryService);
        ProductView productView = new ProductView(productService);
        OrderView orderView = new OrderView(orderService);

        return new MenuApp(customerView, adminView, productView, categoryView, orderView, authService);
    }
}
