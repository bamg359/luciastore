package storeapp.services;

import storeapp.domain.Customer;
import storeapp.services.input.CustomerAdminService;
import storeapp.services.outputport.CustomerPersistencePort;

import java.util.List;

public class CustomerAdminServiceImpl implements CustomerAdminService {


    private final CustomerPersistencePort customerPersistencePort;

    public CustomerAdminServiceImpl(CustomerPersistencePort customerPersistencePort ){
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public List<Customer> getAllCustomers() {
        System.out.println("Customer Admin Service Impl");
        return customerPersistencePort.findAllCustomers();
    }

    @Override
    public void deleteCustomer(int id) {
        customerPersistencePort.deleteCustomer(id);
    }
}
