package com.hmsapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String username;
    private String email;
    private String mobile;
    private String password;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Implementing UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You would usually return roles or permissions here
        return Collections.emptyList(); // Empty list for now (you can customize later)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming the user is enabled
    }
}
