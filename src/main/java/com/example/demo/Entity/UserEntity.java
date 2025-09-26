package com.example.demo.Entity;

import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.demo.Entity.CategoriesEntity;
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fullname;
	    @Column(unique = true)
	    private String email;
	    private String password;
	    private String phone;

	    @ManyToMany
	    @JoinTable(
	            name = "user_category",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "category_id")
	    )
	    private Set<CategoriesEntity> categories = new HashSet<>();

	    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<ProductEntity> products = new ArrayList<>();
}