package storeapp.services.input;

import storeapp.domain.Customer;

import java.util.Optional;

public interface CustumerService {

    // Estos metodos abstratos se configuran en el contrato
    public Customer createCustomer(int idCustomer,String name,String lastName,String email,String password,boolean customerState,double quote,String customerType);
    public Customer getCustomerById(int id);
    public Optional<Customer> getCustomerByEmail(String email);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(int id);




}
