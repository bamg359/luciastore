package storeapp.services;

import storeapp.domain.Admin;
import storeapp.domain.Customer;
import storeapp.domain.enums.AdminPermission;

import java.util.Optional;

public class AuthContext {

    private Admin currentAdmin;
    private Customer currentCustomer;

    public void login(Admin admin) {
        loginAdmin(admin);
    }

    public void loginAdmin(Admin admin) {
        this.currentAdmin = admin;
        this.currentCustomer = null;
    }

    public void loginCustomer(Customer customer) {
        this.currentCustomer = customer;
        this.currentAdmin = null;
    }

    public void logout() {
        this.currentAdmin = null;
        this.currentCustomer = null;
    }

    public Optional<Admin> getCurrentAdmin() {
        return Optional.ofNullable(currentAdmin);
    }

    public Optional<Customer> getCurrentCustomer() {
        return Optional.ofNullable(currentCustomer);
    }

    public boolean isAuthenticated() {
        return currentAdmin != null || currentCustomer != null;
    }

    public boolean isAdminAuthenticated() {
        return currentAdmin != null;
    }

    public boolean isCustomerAuthenticated() {
        return currentCustomer != null;
    }

    public String getCurrentRole() {
        if (currentAdmin != null) {
            return "ADMIN";
        }
        if (currentCustomer != null) {
            return "CUSTOMER";
        }
        return "NONE";
    }

    public boolean hasPermission(AdminPermission permission) {
        return currentAdmin != null && currentAdmin.hasPermission(permission);
    }
}

