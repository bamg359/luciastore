package storeapp.services.input;

import storeapp.domain.Customer;

import java.util.List;

public interface CustomerAdminService {

    public List<Customer> getAllCustomers();
    public void deleteCustomer(int id);
}
