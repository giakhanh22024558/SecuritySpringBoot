package com.example.securitydemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    // API: Lấy thông tin người dùng hiện tại
    @GetMapping("/user/profile")
    public String getUserProfile(Authentication authentication) {
        return "Hello, " + authentication.getName() + "! Your roles: " + getRoles(authentication);
    }

    // API: Chỉ dành cho Admin
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "Access Denied! You must be an admin.";
        }
        return "Welcome to the Admin Dashboard, " + authentication.getName();
    }

    // API: Cập nhật thông tin người dùng
    @PutMapping("/user/update")
    public String updateUser(Authentication authentication) {
        return "User " + authentication.getName() + " has updated their profile.";
    }

    private String getRoles(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return String.join(", ", roles);
    }
}
