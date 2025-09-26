package com.example.demo.Entity;
import lombok.*;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
@Data
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Only one column should be marked with this
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "images")
    private String images;

    @ManyToMany(mappedBy = "categories")
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<ProductEntity> products;
}