package storeapp.persistence.repository;

import storeapp.domain.Customer;
import storeapp.persistence.mapper.CustomerRowMapper;
import storeapp.persistence.mapper.RowMapper;
import storeapp.services.outputport.CustomerPersistencePort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryAdapterMySql implements CustomerPersistencePort {


    private final Connection connection;
    private final CustomerRowMapper rowMapper;

    public CustomerRepositoryAdapterMySql(Connection connection, CustomerRowMapper rowMapper){
        this.connection = connection;
        this.rowMapper = rowMapper;
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

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                customers.add(rowMapper.mapRow(rs));
            }

        }catch(SQLException e){
            throw new RuntimeException("error al buscar los datos " + e);
        }
        System.out.println("Estoy en el Repositorio MYSQL");
        return customers;
    }

    @Override
    public Customer findCustomerById(int id) {


        String sql = "SELECT * FROM customer WHERE id_customer = ? ";

        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Customer customer = rowMapper.mapRow(rs);

                return customer;
            }

        }catch (SQLException e){
            throw new RuntimeException("Cliente con id" + id + "no existe");

        }

        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        String sql= "UPDATE customer SET phone = ? , email = ? , password = ? WHERE id_customer = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){

            setCustomerParams(ps, customer);

            ps.setInt(4, customer.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("CLiente no se puede actualizar");
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        String sql = "DELETE FROM customer WHERE id_customer = ?";


        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);

            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("No se puede eliminar cliente", e );
        }

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
