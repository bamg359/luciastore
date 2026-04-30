package storeapp.services;

import storeapp.domain.Admin;
import storeapp.domain.AuthResult;
import storeapp.domain.Customer;
import storeapp.domain.enums.AuthRole;
import storeapp.repository.AdminRepository;
import storeapp.repository.CustomerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthService {

    private static final int MAX_FAILED_ATTEMPTS = 3;

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final AuthContext authContext;
    private final Map<String, Integer> failedAttemptsByEmail = new HashMap<>();

    public AuthService(AdminRepository adminRepository,
                       CustomerRepository customerRepository,
                       AuthContext authContext) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.authContext = authContext;
    }

    public AuthResult login(String email, String password) {
        String normalizedEmail = normalizeEmail(email);

        if (isMaxAttemptsReached(normalizedEmail)) {
            return AuthResult.failure("Máximo de intentos fallidos alcanzado (3). Contacte al administrador.");
        }

        Admin admin = adminRepository.findAdminByEmail(normalizedEmail);
        if (admin != null && admin.getPassword().equals(password)) {
            if (!admin.isStatus()) {
                return AuthResult.failure("Su cuenta de administrador está inactiva.");
            }

            resetFailedAttempts(normalizedEmail);
            authContext.loginAdmin(admin);
            return AuthResult.success(AuthRole.ADMIN, "Bienvenido administrador " + admin.getName());
        }

        Optional<Customer> customer = customerRepository.findByEmailAndPassword(normalizedEmail, password);
        if (customer.isPresent()) {
            if (!customer.get().isStatus()) {
                return AuthResult.failure("Su cuenta de cliente está inactiva.");
            }

            resetFailedAttempts(normalizedEmail);
            authContext.loginCustomer(customer.get());
            return AuthResult.success(AuthRole.CUSTOMER, "Bienvenido " + customer.get().getName());
        }

        int failedAttempts = registerFailedAttempt(normalizedEmail);
        int remainingAttempts = Math.max(0, MAX_FAILED_ATTEMPTS - failedAttempts);

        if (remainingAttempts == 0) {
            return AuthResult.failure("Credenciales inválidas. Máximo de intentos fallidos alcanzado (3).");
        }

        return AuthResult.failure("Credenciales inválidas. Intentos restantes: " + remainingAttempts);
    }

    public void logout() {
        authContext.logout();
    }

    public Optional<Admin> getCurrentAdmin() {
        return authContext.getCurrentAdmin();
    }

    public Optional<Customer> getCurrentCustomer() {
        return authContext.getCurrentCustomer();
    }

    public boolean isAuthenticated() {
        return authContext.isAuthenticated();
    }

    public AuthRole getCurrentRole() {
        String role = authContext.getCurrentRole();
        if ("ADMIN".equals(role)) {
            return AuthRole.ADMIN;
        }
        if ("CUSTOMER".equals(role)) {
            return AuthRole.CUSTOMER;
        }
        return AuthRole.NONE;
    }

    private boolean isMaxAttemptsReached(String email) {
        return failedAttemptsByEmail.getOrDefault(email, 0) >= MAX_FAILED_ATTEMPTS;
    }

    private int registerFailedAttempt(String email) {
        int attempts = failedAttemptsByEmail.getOrDefault(email, 0) + 1;
        failedAttemptsByEmail.put(email, attempts);
        return attempts;
    }

    private void resetFailedAttempts(String email) {
        failedAttemptsByEmail.remove(email);
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}