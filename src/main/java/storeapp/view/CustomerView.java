package storeapp.view;

import storeapp.domain.Customer;
import storeapp.services.CustomerStateSelector;
import storeapp.services.CustomerTypeSelector;
import storeapp.services.input.CustumerService;
import storeapp.utils.FormValidation;

public class CustomerView {

    private final CustumerService customerService;


    public CustomerView(CustumerService customerService){
        this.customerService = customerService;
    }

    public void createCustomer(){



        int idCustomer = FormValidation.validateInt("Ingrese el id del cliente");
        String name = FormValidation.validateString("Ingrese el nombre del cliente");
        String lastName = FormValidation.validateString("Ingrese el apellido");
        String email = FormValidation.validateString("ingrese el email");
        String password = FormValidation.validateString("Ingrese el password ");
        System.out.println("Estado Cliente ");
        Boolean customerState = CustomerStateSelector.selectCustomerState();
        double quote = FormValidation.validateDouble("Cupo");
        System.out.println("Tipo de Cliente");
        String customerType = CustomerTypeSelector.selectTypeCustomer();

        customerService.createCustomer(idCustomer,name,lastName,email,password,customerState, quote, customerType);


    }


    public void getCustumerById(){

        int id = FormValidation.validateInt("Ingrese el Id del cliente a buscar");
        Customer customer = customerService.getCustomerById(id);

        System.out.println("id: " + customer.getId() + "\n" +
                "Nombre: " + customer.getName() + "\n" +
                "Apellido" + customer.getLastName() + "\n" +
                "Correo" + customer.getEmail()+ "\n");


    }


    public void updateCustumer(){
        System.out.println("Estoy en la Vista");

            int id = FormValidation.validateInt("Ingrese el id a actualizar");

            Customer customer  = customerService.getCustomerById(id);

            if(customer != null){

            System.out.println("Actualizar 1.Correo 2. Contraseña");
            int option = FormValidation.validateInt("Opcion");

            switch (option){

                case 1:
                    customer.setEmail(FormValidation.validateString("Actualizar Email"));
                    break;
                case 2:
                    customer.setPassword(FormValidation.validateString("Actualizar contraseña"));
                default:
                    System.out.println("Seleccione una opcion");
                    break;
            }

            }else{
                System.out.println("Cliente no encontrado");
            }

            customerService.updateCustomer(customer);

    }

    public void deleteCustomer(){
        customerService.deleteCustomer(FormValidation.validateInt("Ingrese el id del CLiente a eliminar"));
    }


}
