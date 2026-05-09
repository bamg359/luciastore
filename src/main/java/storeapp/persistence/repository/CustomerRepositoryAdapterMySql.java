package storeapp.persistence.repository;

import storeapp.domain.Customer;
import storeapp.services.outputport.CustomerPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepositoryAdapterMySql implements CustomerPersistencePort {


    private final Connection connection;

    public CustomerRepositoryAdapterMySql(Connection connection){
        this.connection = connection;
    }


    @Override
    public Customer saveCustomer(Customer customer) {

        String sql = "INSERT INTO customer (id_customer, name, last_name, email, password, status, quote, type) VALUES (?,?,?,?,?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS) ){

            setCustomerParams(ps , customer);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){

                customer.setId(keys.getInt(1));
            }

        }catch(SQLException e){
           throw new RuntimeException("Error al guardar el cliente", e);
        }


        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return List.of();
    }

    @Override
    public Customer findCustomerById(int id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(int id) {

    }


    //Helper Methods

    private void setCustomerParams(PreparedStatement ps , Customer customer) throws SQLException{

        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getName());
        ps.setString(3, customer.getLastName());
        ps.setString(4, customer.getEmail());
        ps.setString(5, customer.getPassword());
        ps.setString(6, String.valueOf(customer.isStatus()));
        ps.setDouble(7, customer.getQuote());
        ps.setString(8, customer.getCustomerType());

    }

}
