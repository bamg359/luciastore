package storeapp.userinterface;

import storeapp.services.AuthService;
import storeapp.utils.CustomerFormValidation;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;

import java.util.Scanner;

public class MenuApp {

    Scanner sc = new Scanner(System.in);

    private final CustomerView customerView;
    private final AdminView    adminView;
    private final ProductView  productView;
    private final CategoryView categoryView;
    private final AuthService authService;

    public MenuApp(CustomerView customerView, AdminView adminView,
                   ProductView productView, CategoryView categoryView,
                   AuthService authService) {
        this.customerView = customerView;
        this.adminView    = adminView;
        this.productView  = productView;
        this.categoryView = categoryView;
        this.authService  = authService;
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

            switch (option) {
                case 1:
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

    public void showMenuCustomer() {
        System.out.println("\n=== MENÚ CLIENTE ===");
        while (true) {
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
                    break;
                case 3:
                    customerView.updateCustumer();
                    break;
                case 4:
                    System.out.println("Saliendo del menu cliente");
                    return;
                default:
                    System.out.println("Opcion no valida");
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

            switch (option) {
                case 1:
                    customerView.createCustomer();
                    break;
                case 2:
                    int id = CustomerFormValidation.validateInt("Ingrese el id del cliente");
                    customerView.getCustumerById(id);
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
            }
        }
    }
}