package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.Entity.UserEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.Entity.CategoriesEntity;
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data  // Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString methods
public class ProductEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer quantity;
    @Column(name = "description", length = 2000)
    private String desc;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    @ManyToMany(mappedBy = "products")
    private Set<CategoriesEntity> categories = new HashSet<>();


}