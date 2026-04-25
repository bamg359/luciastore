package storeapp.userinterface;

import storeapp.view.AdminView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;
import storeapp.view.CategoryView;
import storeapp.view.OrderView;
import storeapp.utils.ProductFormValidation;

public class MenuApp {

    private final CustomerView customerView;
    private final AdminView adminView;
    private final ProductView productView;
    private final CategoryView categoryView;
    private final OrderView orderView;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView, CategoryView categoryView, OrderView orderView) {
        this.customerView = customerView;
        this.adminView = adminView;
        this.productView = productView;
        this.categoryView = categoryView;
        this.orderView = orderView;
    }

    public void showMainMenu() {
        System.out.println("--- BIENVENIDO A LA TIENDA ONLINE ---");
        int init = ProductFormValidation.validateInt("Presione 1 para iniciar (0 para salir):");

        while (init != 0) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Iniciar Sesión\n2. Salir");
            int option = ProductFormValidation.validateInt("Seleccione una opción:");

            switch (option) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.out.println("Saliendo de la aplicación...");
                    init = 0;
                    break;
                default:
                    System.out.println("❎ Opción no válida.");
            }
        }
    }


    private void iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.println("1. Perfil Administrador\n2. Perfil Cliente");
        int profileType = ProductFormValidation.validateInt("Seleccione perfil:");

        if (profileType == 1) {
            profileSelector("admin");
        } else if (profileType == 2) {
            profileSelector("customer");
        } else {
            System.out.println("❎ Perfil no reconocido.");
        }
    }

    public void profileSelector(String profile) {
        if (profile.equals("admin")) {
            showMenuAdmin();
        } else if (profile.equals("customer")) {
            showMenuCustomer();
        }
    }

    // --- MENÚ ADMINISTRADOR ---
    public void showMenuAdmin() {
        while (true) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Gestionar Productos\n2. Gestionar Categorías\n3. Gestionar Clientes\n4. Cerrar Sesión");
            int option = ProductFormValidation.validateInt("Seleccione:");

            switch (option) {
                case 1: gestionarProductos(); break;
                case 2: gestionarCategorias(); break;
                case 3: customerMenuAdmin(); break;
                case 4: return;
                default: System.out.println("❎ Opción no válida.");
            }
        }
    }

    private void gestionarProductos() {
        System.out.println("\n--- Módulo Productos (Admin) ---");
        System.out.println("1. Registrar\n2. Actualizar\n3. Eliminar\n4. Listar Todo");
        int opt = ProductFormValidation.validateInt("Seleccione:");
        switch (opt) {
            case 1: productView.createProduct(); break;
            case 2: productView.updateProduct(); break;
            case 3: productView.deleteProduct(); break;
            case 4: productView.getAllProducts(); break;
            default: System.out.println("❎ Opción inválida.");
        }
    }

    private void gestionarCategorias() {
        System.out.println("\n--- Módulo Categorías (Admin) ---");
        System.out.println("1. Registrar\n2. Eliminar\n3. Listar Todo");
        int opt = ProductFormValidation.validateInt("Seleccione:");
        switch (opt) {
            case 1: categoryView.createCategory(); break;
            case 2: categoryView.deleteCategory(); break;
            case 3: categoryView.getAllCategories(); break;
            default: System.out.println("❎ Opción inválida.");
        }
    }

    // --- MENÚ CLIENTE ---
    public void showMenuCustomer() {
        while (true) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. VER CATÁLOGO DE PRODUCTOS (Todos)");
            System.out.println("2. BUSCAR PRODUCTO POR ID");
            System.out.println("3. Ver mi Perfil");
            System.out.println("4. Modificar mi Perfil");
            System.out.println("5. Realizar Pedido");
            System.out.println("6. Ver mis Pedidos");
            System.out.println("7. Cerrar Sesión");

            int option = ProductFormValidation.validateInt("Seleccione una opción:");

            switch (option) {
                case 1:
                    System.out.println("\n--- Catálogo de Productos ---");
                    productView.getAllProducts(); // Ahora el cliente puede verlos
                    break;
                case 2:
                    int id = ProductFormValidation.validateInt("Ingrese el ID del producto:");
                    productView.getProductById(id); // Ahora el cliente puede buscar
                    break;
                case 3:
                    int myId = ProductFormValidation.validateInt("Ingrese su ID para validar:");
                    customerView.getCustumerById(myId);
                    break;
                case 4:
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
                    return;
                default:
                    System.out.println("❎ Opción no válida.");
            }
        }
    }

    public void customerMenuAdmin() {
        while (true) {
            System.out.println("\n--- Módulo Clientes (Admin) ---");
            System.out.println("1. Crear\n2. Buscar por ID\n3. Modificar\n4. Ver Todos\n5. Eliminar\n6. Volver");
            int option = ProductFormValidation.validateInt("Seleccione:");
            switch (option) {
                case 1: customerView.createCustomer(); break;
                case 2: customerView.getCustumerById(ProductFormValidation.validateInt("ID:")); break;
                case 3: customerView.updateCustumer(); break;
                case 4: adminView.getAllCustomers(); break;
                case 5: customerView.deleteCustomer(); break;
                case 6: return;
                default: System.out.println("❎ Opción inválida.");
            }
        }
    }
}