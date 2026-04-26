package storeapp.userinterface;

import storeapp.domain.Customer;
import storeapp.services.CustumerServiceImpl;
import storeapp.utils.CustomerFormValidation;
import storeapp.view.AdminView;
import storeapp.view.CategoryView;
import storeapp.view.CustomerView;
import storeapp.view.PersonView;
import storeapp.view.ProductView;

import java.util.Scanner;

public class MenuApp {


    Scanner sc = new Scanner(System.in);
    private final CustomerView customerView;
    private final AdminView adminView;
    private final ProductView productView;
    private final CategoryView categoryView;
    private final PersonView personView;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView, CategoryView categoryView, PersonView personView) {
        this.customerView = customerView;
        this.adminView = adminView;
        this.productView = productView;
        this.categoryView = categoryView;
        this.personView = personView;
    }

    public void showMainMenu(){

        System.out.println("Bienvenido a la tienda online");
        System.out.println("Presione 1 para iniciar la aplicacion");

        int init = sc.nextInt();
        sc.nextLine();

        while(init != 0){

            System.out.println("Selecione 1. Registrar Usuario 2. Iniciar Sesion 3. Salir");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.println("Registrar Usuario");
                    System.out.println("1. Cliente 2. Administrador");
                    int userType = sc.nextInt();
                    sc.nextLine();
                    if (userType == 1){
                        customerView.createCustomer();
                    }else if(userType == 2){
                        adminView.createAdmin();
                    }else{
                        System.out.println("Opcion no valida, por favor seleccione una opcion valida");
                    }

                    break;
                case 2:
                    System.out.println("Iniciar Sesion");
                    System.out.println("Seleccione tipo de usuario: 1. Administrador 2. Cliente");
                    userType = sc.nextInt();
                    sc.nextLine();
                    if (userType == 1) {
                        profileSelector("admin");
                    } else if (userType == 2) {
                        profileSelector("customer");
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicacion");
                    init = 0;
                    break;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }


    public void profileSelector(String profile){

        if(profile.equals("admin")){
            showMenuAdmin();
        }else if(profile.equals("customer")){
            showMenuCustomer();
        }
    }


    public void showMenuAdmin(){

        while (true){
            System.out.println("Menu Administrador");
            System.out.println("1. Gestionar Productos 2. Gestionar Categorias 3. Gestionar Clientes 4. Gestionar Personas 5. Salir");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.println("Gestionar Productos");
                    productMenuAdmin();
                    break;
                case 2:
                    System.out.println("Gestionar Categorias");
                    categoryMenuAdmin();
                    break;
                case 3:
                    System.out.println("Gestionar Clientes");
                    customerMenuAdmin();
                    break;
                case 4:
                    System.out.println("Gestionar Personas");
                    personMenuAdmin();
                    break;
                case 5:
                    System.out.println("Saliendo del menu de administrador");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }


    public void showMenuCustomer(){

        System.out.println("Menu Cliente");
        while (true) {

            System.out.println("1. Crear mi perfil 2. Ver mi perfil por id 3. Modifica mi perfil 4. Ver productos disponibles 5. Comprar producto 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear mi perfil");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Ver mi  perfil");
                    System.out.println("Ingrese su id para ver su perfil");
                    int id = sc.nextInt();
                    customerView.getCustumerById(id);
                    break;
                case 3:
                    System.out.println("Modificar mi perfil");
                    customerView.updateCustumer();
                    break;
                case 4:
                    System.out.println("Ver productos disponibles");
                    productView.getAllProducts();
                    break;
                case 5:
                    System.out.println("Comprar producto");
                    productView.purchaseProduct();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de cliente");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }


    public void customerMenuAdmin(){

        System.out.println("Menu Cliente");
        while (true) {

            System.out.println("1. Crear Perfil Cliente 2. Ver perfil por id 3. Modifica perfil 4. Ver perfiles 5. eliminar Perfil 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear perfil");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Ver perfil por id");
                    System.out.println("Buscar perfil");
                    System.out.println("Ingrese el id del  perfil a buscar");
                    int id = sc.nextInt();
                    customerView.getCustumerById(id);
                    break;
                case 3:
                    System.out.println("Modificar perfil");
                    customerView.updateCustumer();
                    break;
                case 4:
                    System.out.println("Ver perfiles");
                    adminView.getAllCustomers();
                    break;
                case 5:
                    System.out.println("Eliminar perfil");
                    customerView.deleteCustomer();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de clientes");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }
    }

    public void productMenuAdmin(){

        System.out.println("Menu Productos");
        while (true) {

            System.out.println("1. Crear Producto 2. Ver producto por id 3. Modifica producto 4. Ver productos 5. Eliminar Producto 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear producto");
                    productView.createProduct();
                    break;
                case 2:
                    System.out.println("Ver producto por id");
                    System.out.println("Ingrese el id del producto a buscar");
                    int id = sc.nextInt();
                    productView.getProductById(id);
                    break;
                case 3:
                    System.out.println("Modificar producto");
                    productView.updateProduct();
                    break;
                case 4:
                    System.out.println("Ver productos");
                    productView.getAllProducts();
                    break;
                case 5:
                    System.out.println("Eliminar producto");
                    productView.deleteProduct();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de productos");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }
    }

    public void categoryMenuAdmin(){

        System.out.println("Menu Categorías");
        while (true) {

            System.out.println("1. Crear Categoría 2. Ver categoría por id 3. Modifica categoría 4. Ver categorías 5. Eliminar Categoría 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear categoría");
                    categoryView.createCategory();
                    break;
                case 2:
                    System.out.println("Ver categoría por id");
                    System.out.println("Ingrese el id de la categoría a buscar");
                    int id = sc.nextInt();
                    categoryView.getCategoryById(id);
                    break;
                case 3:
                    System.out.println("Modificar categoría");
                    categoryView.updateCategory();
                    break;
                case 4:
                    System.out.println("Ver categorías");
                    categoryView.getAllCategories();
                    break;
                case 5:
                    System.out.println("Eliminar categoría");
                    categoryView.deleteCategory();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de categorías");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }
    }

    public void personMenuAdmin(){

        System.out.println("Menu Personas");
        while (true) {

            System.out.println("1. Crear Persona 2. Ver persona por id 3. Modifica persona 4. Ver personas 5. Eliminar Persona 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear persona");
                    personView.createPerson();
                    break;
                case 2:
                    System.out.println("Ver persona por id");
                    System.out.println("Ingrese el id de la persona a buscar");
                    int id = sc.nextInt();
                    personView.getPersonById(id);
                    break;
                case 3:
                    System.out.println("Modificar persona");
                    personView.updatePerson();
                    break;
                case 4:
                    System.out.println("Ver personas");
                    personView.getAllPersons();
                    break;
                case 5:
                    System.out.println("Eliminar persona");
                    personView.deletePerson();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de personas");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }
    }
}
