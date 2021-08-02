package dev.paulmatthews.hashingsalting.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
public class User {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String userName;

    private String passwordHash;

    public User() {}

    private User(String userName, String password) {
        this.userName = userName;
        this.passwordHash = encoder.encode(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public static User fromRegisterUserDTO(RegisterUserDTO registerUserDTO) {
        return new User(registerUserDTO.getUsername(), registerUserDTO.getPassword());
    }

}
