package storeapp.userinterface;

import storeapp.domain.AuthResult;
import storeapp.domain.Customer;
import storeapp.domain.enums.AdminPermission;
import storeapp.domain.enums.AuthRole;
import storeapp.services.AuthService;
import storeapp.utils.ProductFormValidation;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.OrderView;
import storeapp.view.ProductView;

import java.util.Optional;

public class MenuApp {

    private final CustomerView customerView;
    private final AdminView adminView;
    private final ProductView productView;
    private final CategoryView categoryView;
    private final OrderView orderView;
    private final AuthService authService;

    public MenuApp(CustomerView customerView, AdminView adminView,
                   ProductView productView, CategoryView categoryView,
                   OrderView orderView, AuthService authService) {
        this.customerView = customerView;
        this.adminView = adminView;
        this.productView = productView;
        this.categoryView = categoryView;
        this.orderView = orderView;
        this.authService = authService;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar Usuario Cliente");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Crear Administrador (requiere admin)");
            System.out.println("4. Salir");

            int option = ProductFormValidation.validateInt("Seleccione una opción:");

            switch (option) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    crearAdminDesdeMenuPrincipal();
                    break;
                case 4:
                    System.out.println("Saliendo de la aplicación...");
                    return;
                default:
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registro Cliente ---");
        customerView.createCustomer();
        System.out.println("ℹ️ Las cuentas de administrador solo se crean desde permisos ADMIN_MANAGE.");
    }

    private void iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        String email = ProductFormValidation.validateString("Ingrese su email:");
        String password = ProductFormValidation.validateString("Ingrese su contraseña:");

        AuthResult result = authService.login(email, password);
        System.out.println(result.getMessage());

        if (!result.isAuthenticated()) {
            return;
        }

        if (result.getRole() == AuthRole.ADMIN) {
            showMenuAdmin();
            return;
        }

        if (result.getRole() == AuthRole.CUSTOMER) {
            showMenuCustomer();
            return;
        }

        System.out.println("❎ No se pudo determinar el rol autenticado.");
        authService.logout();
    }

    private void crearAdminDesdeMenuPrincipal() {
        System.out.println("\n--- Crear Nuevo Administrador ---");
        String email = ProductFormValidation.validateString("Email admin autenticado:");
        String password = ProductFormValidation.validateString("Password admin autenticado:");

        AuthResult result = authService.login(email, password);
        if (!result.isAuthenticated() || result.getRole() != AuthRole.ADMIN) {
            System.out.println("❎ Debe autenticarse como administrador activo.");
            return;
        }

        if (!adminView.hasPermission(AdminPermission.ADMIN_MANAGE)) {
            System.out.println("❎ No tiene permiso para crear administradores.");
            authService.logout();
            return;
        }

        adminView.createNewAdmin();
        authService.logout();
        System.out.println("✅ Operación finalizada y sesión cerrada.");
    }

    public void showMenuAdmin() {
        while (true) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Categorías");
            System.out.println("3. Gestionar Clientes");
            System.out.println("4. Gestionar Administradores");
            System.out.println("5. Cerrar Sesión");

            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1:
                    if (requirePermission(AdminPermission.PRODUCT_MANAGE)) {
                        gestionarProductos();
                    }
                    break;
                case 2:
                    if (requirePermission(AdminPermission.CATEGORY_MANAGE)) {
                        gestionarCategorias();
                    }
                    break;
                case 3:
                    if (requirePermission(AdminPermission.CUSTOMER_MANAGE)) {
                        customerMenuAdmin();
                    }
                    break;
                case 4:
                    if (requirePermission(AdminPermission.ADMIN_MANAGE)) {
                        adminMenuAdmin();
                    }
                    break;
                case 5:
                    authService.logout();
                    System.out.println("✅ Sesión cerrada correctamente.");
                    return;
                default:
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    private boolean requirePermission(AdminPermission permission) {
        if (!adminView.hasPermission(permission)) {
            System.out.println("❎ Acceso denegado. Permiso requerido: " + permission);
            return false;
        }
        return true;
    }

    private void gestionarProductos() {
        System.out.println("\n--- Módulo Productos (Admin) ---");
        System.out.println("1. Registrar\n2. Actualizar\n3. Eliminar\n4. Listar Todo");

        int opt = ProductFormValidation.validateInt("Seleccione:");

        switch (opt) {
            case 1:
                productView.createProduct();
                break;
            case 2:
                productView.updateProduct();
                break;
            case 3:
                productView.deleteProduct();
                break;
            case 4:
                productView.getAllProducts();
                break;
            default:
                System.out.println("❎ Opción inválida.");
                break;
        }
    }

    private void gestionarCategorias() {
        System.out.println("\n--- Módulo Categorías (Admin) ---");
        System.out.println("1. Registrar\n2. Eliminar\n3. Listar Todo");

        int opt = ProductFormValidation.validateInt("Seleccione:");

        switch (opt) {
            case 1:
                categoryView.createCategory();
                break;
            case 2:
                categoryView.deleteCategory();
                break;
            case 3:
                categoryView.getAllCategories();
                break;
            default:
                System.out.println("❎ Opción inválida.");
                break;
        }
    }

    private void adminMenuAdmin() {
        while (true) {
            System.out.println("\n--- Módulo Administradores (Admin) ---");
            System.out.println("1. Crear nuevo admin");
            System.out.println("2. Buscar admin por ID");
            System.out.println("3. Actualizar admin");
            System.out.println("4. Listar todos los admins");
            System.out.println("5. Eliminar admin");
            System.out.println("6. Volver");

            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1:
                    adminView.createNewAdmin();
                    break;
                case 2:
                    adminView.getAdminById();
                    break;
                case 3:
                    adminView.updateAdmin();
                    break;
                case 4:
                    adminView.getAllAdmins();
                    break;
                case 5:
                    adminView.deleteAdmin();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❎ Opción inválida.");
                    break;
            }
        }
    }

    public void showMenuCustomer() {
        while (true) {
            Optional<Customer> currentCustomerOpt = authService.getCurrentCustomer();
            if (!currentCustomerOpt.isPresent()) {
                System.out.println("❎ No hay sesión de cliente activa.");
                return;
            }

            Customer currentCustomer = currentCustomerOpt.get();

            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Ver mi perfil");
            System.out.println("4. Modificar mi perfil");
            System.out.println("5. Realizar pedido");
            System.out.println("6. Ver mis pedidos");
            System.out.println("7. Cerrar sesión");

            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1:
                    productView.getAllProducts();
                    break;
                case 2:
                    productView.getProductById(ProductFormValidation.validateInt("Ingrese ID de producto:"));
                    break;
                case 3:
                    customerView.getCustumerById(currentCustomer.getId());
                    break;
                case 4:
                    customerView.updateCustumer();
                    break;
                case 5:
                    orderView.createOrder(currentCustomer.getId());
                    break;
                case 6:
                    orderView.viewMyOrders(currentCustomer.getId());
                    break;
                case 7:
                    authService.logout();
                    System.out.println("✅ Sesión cerrada correctamente.");
                    return;
                default:
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    public void customerMenuAdmin() {
        while (true) {
            System.out.println("\n--- Módulo Clientes (Admin) ---");
            System.out.println("1. Crear");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Modificar");
            System.out.println("4. Ver todos los clientes");
            System.out.println("5. Eliminar");
            System.out.println("6. Volver");

            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1:
                    customerView.createCustomer();
                    break;
                case 2:
                    customerView.getCustumerById(ProductFormValidation.validateInt("ID:"));
                    break;
                case 3:
                    customerView.updateCustumer();
                    break;
                case 4:
                    adminView.getAllCustomers();
                    break;
                case 5:
                    customerView.deleteCustomer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❎ Opción inválida.");
                    break;
            }
        }
    }
}

