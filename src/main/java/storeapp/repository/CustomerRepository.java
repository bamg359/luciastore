package storeapp.repository;

import storeapp.domain.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "John", "Doe",   "jd@mail.com", "1234567890", true, 1000000.00, "NUEVO"),
            new Customer(2, "Jane", "Smith", "js@mail.com", "1234567890", true, 2000000.00, "ANTIGUO")
    ));

    public Customer saveCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> findAllCustomers() {
        return customers;
    }

    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public Optional<Customer> findByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)
                    && customer.getPassword().equals(password)) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public Customer updateCustomer(int id) {
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                return customer;
            }
        }
        return null;
    }



    public void deleteCustomer(int id){
        customers.removeIf(customer -> customer.getId() == id);
    }
}