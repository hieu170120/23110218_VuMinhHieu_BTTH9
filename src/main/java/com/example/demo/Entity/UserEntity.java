package com.example.demo.Entity;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.util.Set;
import com.example.demo.Entity.CategoriesEntity;
@Entity
@Table(name = "users")
@Data  // Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString methods
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ManyToMany
    @JoinTable(
        name = "category_user", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoriesEntity> categories; // Many-to-many relationship with Categories
}