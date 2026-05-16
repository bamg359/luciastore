package storeapp.services;

import storeapp.domain.Customer;
import storeapp.services.input.CustumerService;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.utils.FormValidation;

import java.util.Optional;
import java.util.Scanner;

public class CustumerServiceImpl implements CustumerService {

    Scanner sc = new Scanner(System.in);

    //Ahora vamos a comunicar las clases , para eso vamos a crear una instancia de la capa inmediatamente anterior
    private final CustomerPersistencePort customerRepository;

    public CustumerServiceImpl( CustomerPersistencePort customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public Customer createCustomer(int idCustomer,String name,String lastName,String email,String password,boolean customerState,double quote,String customerType) {

        Customer customer = new Customer(idCustomer,name,lastName,email,password,customerState, quote, customerType);

        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) {

        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public Customer updateCustomer(Customer customer) {

        System.out.println("Estoy en el service");
        Customer customer1 = customer;
        System.out.println("Bebug" + customer1.getPassword());
        customerRepository.updateCustomer(customer1);

        return customer1;
    }

    @Override
    public void deleteCustomer(int id) {

        customerRepository.deleteCustomer(id);

    }
}
