package storeapp.view;

import storeapp.domain.Customer;
import storeapp.services.AdminServiceImpl;
import storeapp.services.input.CustomerAdminService;
import storeapp.utils.FormValidation;

import javax.swing.*;
import java.util.List;

import java.util.Scanner;

public class AdminView {

    Scanner sc = new Scanner(System.in);
    private final AdminServiceImpl adminService;
    private final CustomerAdminService customerAdminService;



    public AdminView(AdminServiceImpl adminService, CustomerAdminService customerAdminService ){
        this.customerAdminService = customerAdminService;
        this.adminService = adminService;

    }

    public void createAdmin(){

    }


    public void getAllCustomers(){

       List<Customer> customers = customerAdminService.getAllCustomers();

       for(Customer customer: customers){
           System.out.println("id:" + customer.getId() + "\n"+"nombre: " + customer.getName() +"\n"+ "Apellido" + customer.getLastName()+ "\n");
       }

    }


    public void deleteCustomerById(){
        System.out.println("Eliminar Cliente");
        int id_customer = FormValidation.validateInt("Ingrese  el id del cliente a eliminar");
        customerAdminService.deleteCustomer(id_customer);
    }

    public  void deleteCustomer(){
        System.out.println("Ingrese el ID del cliente a eliminar");
        int id = sc.nextInt();
        sc.nextLine();
        adminService.deleteCustomer(id);
        System.out.println("Cliente eliminado");
    }

}
