package dev.paulmatthews.hashingsalting.models;

public class RegisterUserDTO {
    private String username;
    private String password;
    private String confirmPassword;

    public RegisterUserDTO(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean confirmPasswords() {
        return password.equals(confirmPassword);
    }
}
