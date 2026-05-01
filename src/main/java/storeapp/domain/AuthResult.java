package storeapp.domain;

import storeapp.domain.enums.AuthRole;

public class AuthResult {

    private final boolean authenticated;
    private final AuthRole role;
    private final String message;

    public AuthResult(boolean authenticated, AuthRole role, String message) {
        this.authenticated = authenticated;
        this.role = role;
        this.message = message;
    }

    public static AuthResult success(AuthRole role, String message) {
        return new AuthResult(true, role, message);
    }

    public static AuthResult failure(String message) {
        return new AuthResult(false, AuthRole.NONE, message);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public AuthRole getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}

