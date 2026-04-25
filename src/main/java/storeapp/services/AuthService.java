package storeapp.services;

import storeapp.domain.Customer;
import storeapp.repository.CustomerRepository;

import java.util.Optional;

public class AuthService {

    private static final String ADMIN_EMAIL    = "admin@mail.com";
    private static final String ADMIN_PASSWORD = "admin123";

    private final CustomerRepository customerRepository;

    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String login(String email, String password) {

        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Bienvenido Administrador");
            return "admin";
        }

        Optional<Customer> customer = customerRepository.findByEmailAndPassword(email, password);
        if (customer.isPresent()) {
            if (!customer.get().isStatus()) {
                System.out.println("Tu cuenta esta inactiva.");
                return "none";
            }
            System.out.println("Bienvenido " + customer.get().getName());
            return "customer";
        }

        System.out.println("Email o contrasena incorrectos.");
        return "none";
    }
}