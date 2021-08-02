package dev.paulmatthews.hashingsalting.models;

public class LoginUserDTO {
    private String username;
    private String password;

    public LoginUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
