package storeapp.view;

import storeapp.domain.Customer;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.utils.CustomerFormValidation;

public class CustomerView {

    private final CustumerService customerService;
    public CustomerView(CustumerService customerService){
        this.customerService = customerService;
    }

    public void createCustomer(){
        customerService.createCustomer();
    }
    public void getCustumerById(int id){

        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            System.out.println("ID: " + customer.getId());
            System.out.println("Nombre: " + customer.getName());
            System.out.println("Apellido: " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Estado: " + customer.isStatus());
            System.out.println("Cupo: " + customer.getQuote());
            System.out.println("Tipo: " + customer.getCustomerType());
        } else {
            System.out.println("❎ Cliente no encontrado");
        }
    }


    public void updateCustumer(){
        customerService.updateCustomer(CustomerFormValidation.validateInt("Ingrese el ID"));

    }

    public void deleteCustomer(){
        customerService.deleteCustomer(CustomerFormValidation.validateInt("Ingrese el id del Cliente a eliminar"));
    }


}
