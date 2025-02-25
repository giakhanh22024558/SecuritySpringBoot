package com.example.securitydemo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter for username
    @Getter
    @Column(nullable = false, unique = true)
    private String username;

    // Getter for password
    @Getter
    @Column(nullable = false)
    private String password;

    // Getter for roles
    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    private Set<String> roles;

    public User() {
    }

    // Constructor with parameters
    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Helper method to stream roles
    public java.util.stream.Stream<String> getRolesStream() {
        return roles.stream();
    }



}
