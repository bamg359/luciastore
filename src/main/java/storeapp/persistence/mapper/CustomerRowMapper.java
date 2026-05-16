package storeapp.persistence.mapper;

import storeapp.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {


    @Override
    public Customer mapRow(ResultSet rs) throws SQLException {

        Customer customer = new Customer();

        customer.setId(rs.getInt("id_customer"));
        customer.setName(rs.getString("name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setStatus(rs.getBoolean("status"));
        customer.setQuote(rs.getDouble("quote"));
        customer.setCustomerType(rs.getString("type"));

        return customer;
    }
}
