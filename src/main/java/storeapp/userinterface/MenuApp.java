package storeapp.userinterface;

import storeapp.services.AuthService;
import storeapp.utils.CustomerFormValidation;
import storeapp.domain.Admin;
import storeapp.domain.enums.AdminPermission;
import storeapp.repository.AdminRepository;
import storeapp.services.AdminServiceImpl;
import storeapp.utils.ProductFormValidation;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;
import storeapp.view.CategoryView;
import storeapp.view.OrderView;
import storeapp.utils.ProductFormValidation;

import java.util.Scanner;
import java.util.Optional;

public class MenuApp {

    Scanner sc = new Scanner(System.in);

    private final CustomerView customerView;
    private final AdminView    adminView;
    private final ProductView  productView;
    private final CategoryView categoryView;
    private final OrderView orderView;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView, CategoryView categoryView, OrderView orderView) {
    private final AuthService authService;

    public MenuApp(CustomerView customerView, AdminView adminView,
                   ProductView productView, CategoryView categoryView,
                   AuthService authService) {
    private final AdminServiceImpl adminService;
    private final AdminRepository adminRepository;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView, 
                   CategoryView categoryView, AdminServiceImpl adminService, AdminRepository adminRepository) {
        this.customerView = customerView;
        this.adminView    = adminView;
        this.productView  = productView;
        this.categoryView = categoryView;
        this.orderView = orderView;
        this.authService  = authService;
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    public void showMainMenu() {

        System.out.println("Bienvenido a la tienda online");
        System.out.println("Presione 1 para iniciar la aplicacion");

        int init = sc.nextInt();
        sc.nextLine();

        while (init != 0) {

            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");

            int option = CustomerFormValidation.validateInt("Seleccione una opcion");
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Iniciar Sesión\n2. Salir");
            System.out.println("1. Registrar Usuario Cliente");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Crear Administrador (Requiere credenciales de admin)");
            System.out.println("4. Salir");

            int option = ProductFormValidation.validateInt("Seleccione una opción:");

            switch (option) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.out.println("1. Cliente  2. Administrador");
                    int userType = CustomerFormValidation.validateInt("Tipo de usuario");
                    if (userType == 1) {
                        customerView.createCustomer();
                    } else if (userType == 2) {
                        adminView.createAdmin();
                    } else {
                        System.out.println("Opcion no valida");
                    }
                    break;

                case 2:
                    // ✅ AUTENTICACIÓN REAL
                    System.out.println("\n=== INICIAR SESIÓN ===");
                    String email    = CustomerFormValidation.validateString("Ingrese su email");
                    String password = CustomerFormValidation.validateString("Ingrese su contraseña");

                    String rol = authService.login(email, password);
                    profileSelector(rol);
                    break;

                case 3:
                    System.out.println("Saliendo de la aplicacion. ¡Hasta luego!");
                    crearAdminDesdeMenuPrincipal();
                    break;
                case 4:
                    System.out.println("Saliendo de la aplicación...");
                    init = 0;
                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    public void profileSelector(String profile) {
        switch (profile) {
            case "admin":
                showMenuAdmin();
                break;
            case "customer":
                showMenuCustomer();
                break;
            default:
                System.out.println("No se pudo iniciar sesion. Verifique sus credenciales.");
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    private void crearAdminDesdeMenuPrincipal() {
        System.out.println("\n--- Crear Nuevo Administrador ---");
        System.out.println("⚠️ Esta operación requiere autenticación de un admin existente con permiso ADMIN_MANAGE.");

        String email = ProductFormValidation.validateString("Email del admin autenticado:");
        String password = ProductFormValidation.validateString("Password del admin autenticado:");

        Optional<Admin> authAdmin = adminService.authenticate(email, password);

        if (!authAdmin.isPresent()) {
            System.out.println("❎ Credenciales inválidas o cuenta inactiva.");
            return;
        }

        if (!adminService.hasPermission(AdminPermission.ADMIN_MANAGE)) {
            System.out.println("❎ No tiene permiso para crear administradores.");
            adminService.logout();
            return;
        }

        System.out.println("✅ Autenticación exitosa. Procediendo a crear nuevo admin...");
        adminView.createNewAdmin();
        adminService.logout();
        System.out.println("✅ Sesión cerrada.");
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registro Cliente ---");
        customerView.createCustomer();
        System.out.println("ℹ️ Las cuentas de administrador solo se gestionan desde el módulo de Admin.");
    }

    private void iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.println("1. Perfil Administrador");
        System.out.println("2. Perfil Cliente");

        int profileType = ProductFormValidation.validateInt("Seleccione perfil:");

        switch (profileType) {
            case 1:
                if (adminView.loginAdmin()) {
                    try {
                        showMenuAdmin();
                    } finally {
                        adminView.logoutAdmin();
                    }
                }
                break;
            case 2:
                showMenuCustomer();
                break;
            default:
                System.out.println("❎ Perfil no reconocido.");
                break;
        }
    }

    public void showMenuAdmin() {
        while (true) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Categorias");
            System.out.println("3. Gestionar Clientes");
            System.out.println("4. Salir");

            int option = CustomerFormValidation.validateInt("Seleccione una opcion");

            switch (option) {
                case 1:
                    System.out.println("Gestionar Productos");
                    // productView.productMenu(); // descomenta cuando tengas el método
                    break;
                case 2:
                    System.out.println("Gestionar Categorias");
                    // categoryView.categoryMenu(); // descomenta cuando tengas el método
                    break;
                case 3:
                    customerMenuAdmin();
                    break;
                case 4:
                    System.out.println("Saliendo del menu administrador");
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

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
                    return;
                default:
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    private boolean requirePermission(AdminPermission permission) {
        if (!adminView.hasPermission(permission)) {
            System.out.println("❎ Acceso denegado. No tiene permiso: " + permission);
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
            System.out.println("6. Volver al menú anterior");

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
        System.out.println("\n=== MENÚ CLIENTE ===");
        while (true) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. VER CATÁLOGO DE PRODUCTOS (Todos)");
            System.out.println("2. BUSCAR PRODUCTO POR ID");
            System.out.println("3. Ver mi Perfil");
            System.out.println("4. Modificar mi Perfil");
            System.out.println("5. Realizar Pedido");
            System.out.println("6. Ver mis Pedidos");
            System.out.println("7. Cerrar Sesión");
            System.out.println("1. Crear mi perfil");
            System.out.println("2. Ver mi perfil por id");
            System.out.println("3. Modificar mi perfil");
            System.out.println("4. Salir");

            int option = CustomerFormValidation.validateInt("Seleccione una opcion");

            switch (option) {
                case 1:
                    customerView.createCustomer();
                    break;
                case 2:
                    int id = CustomerFormValidation.validateInt("Ingrese su id");
                    customerView.getCustumerById(id);
                    System.out.println("\n--- Catálogo de Productos ---");
                    productView.getAllProducts();
                    break;
                case 2:
                    int id = ProductFormValidation.validateInt("Ingrese el ID del producto:");
                    productView.getProductById(id);
                    break;
                case 3:
                    customerView.updateCustumer();
                    break;
                case 5:
                    int customerId = ProductFormValidation.validateInt("Ingrese su ID de cliente:");
                    orderView.createOrder(customerId);
                    break;
                case 6:
                    int custId = ProductFormValidation.validateInt("Ingrese su ID de cliente:");
                    orderView.viewMyOrders(custId);
                    break;
                case 7:
                    System.out.println("Cerrando sesión de cliente...");
                case 4:
                    System.out.println("Saliendo del menu cliente");
                    return;
                default:
                    System.out.println("Opcion no valida");
                    System.out.println("❎ Opción no válida.");
                    break;
            }
        }
    }

    public void customerMenuAdmin() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        while (true) {
            System.out.println("1. Crear perfil cliente");
            System.out.println("2. Ver perfil por id");
            System.out.println("3. Modificar perfil");
            System.out.println("4. Ver todos los clientes");
            System.out.println("5. Eliminar perfil");
            System.out.println("6. Volver");

            int option = CustomerFormValidation.validateInt("Seleccione una opcion");
            System.out.println("\n--- Módulo Clientes (Admin) ---");
            System.out.println("1. Crear\n2. Buscar por ID\n3. Modificar\n4. Ver Todos\n5. Eliminar\n6. Volver");

            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1:
                    customerView.createCustomer();
                    break;
                case 2:
                    int id = CustomerFormValidation.validateInt("Ingrese el id del cliente");
                    customerView.getCustumerById(id);
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
                    System.out.println("Opcion no valida");
                    System.out.println("❎ Opción inválida.");
                    break;
            }
        }
    }
}

