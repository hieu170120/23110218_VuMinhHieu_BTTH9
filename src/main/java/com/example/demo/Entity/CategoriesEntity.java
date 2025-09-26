package com.example.demo.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Entity.ProductEntity;
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriesEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String images;

    @ManyToMany(mappedBy = "categories")
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new HashSet<>();
}