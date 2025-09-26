package com.example.demo.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.Entity.CategoriesEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.DTO.UserInput;
import com.example.demo.DTO.ProductInput;
import com.example.demo.DTO.CategoryInput;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphQLController {
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    @QueryMapping
    public List<ProductEntity> productsByPriceAsc() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<ProductEntity> productsByCategory(@Argument Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<UserEntity> users() { return userRepo.findAll(); }
    @QueryMapping
    public List<ProductEntity> products() { return productRepo.findAll(); }
    @QueryMapping
    public List<CategoriesEntity> categories() { return categoryRepo.findAll(); }

    @QueryMapping
    public UserEntity user(@Argument Long id) { return userRepo.findById(id).orElse(null); }
    @QueryMapping
    public ProductEntity product(@Argument Long id) { return productRepo.findById(id).orElse(null); }
    @QueryMapping
    public CategoriesEntity category(@Argument Long id) { return categoryRepo.findById(id).orElse(null); }

    @SchemaMapping(typeName = "Product", field = "owner")
    public UserEntity owner(ProductEntity product) { return product.getOwner(); }

    @SchemaMapping(typeName = "User", field = "products")
    public List<ProductEntity> userProducts(UserEntity user) {
        return productRepo.findAll().stream().filter(p -> p.getOwner()!=null && Objects.equals(p.getOwner().getId(), user.getId())).toList();
    }

    @SchemaMapping(typeName = "Category", field = "products")
    public List<ProductEntity> products(CategoriesEntity category) {
        return productRepo.findByCategoryId(category.getId());
    }

    @MutationMapping
    @Transactional
    public UserEntity createUser(@Argument UserInput input) {
        UserEntity u = new UserEntity();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());

        if (input.categoryIds() != null && !input.categoryIds().isEmpty()) {
            Set<CategoriesEntity> newCats = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            u.getCategories().clear();
            u.getCategories().addAll(newCats);
        }
        return userRepo.save(u);
    }

    @MutationMapping
    @Transactional
    public UserEntity updateUser(@Argument Long id, @Argument UserInput input) {
        UserEntity u = userRepo.findById(id).orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("User not found: " + id));

        if (input.fullname() != null) u.setFullname(input.fullname());
        if (input.email() != null)    u.setEmail(input.email());
        if (input.password() != null) u.setPassword(input.password());
        if (input.phone() != null)    u.setPhone(input.phone());

        if (input.categoryIds() != null) {
            Set<CategoriesEntity> newCats = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            u.getCategories().clear();
            u.getCategories().addAll(newCats);
        }

        return userRepo.save(u);
    }

    @MutationMapping
    @Transactional
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

    @MutationMapping
    @Transactional
    public ProductEntity createProduct(@Argument ProductInput input) {
        ProductEntity p = new ProductEntity();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        if (input.ownerId()!=null) {
            UserEntity owner = userRepo.findById(input.ownerId()).orElse(null);
            p.setOwner(owner);
        }
        ProductEntity saved = productRepo.save(p);

        if (input.categoryIds()!=null && !input.categoryIds().isEmpty()) {
            Set<CategoriesEntity> cats = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            cats.forEach(c -> c.getProducts().add(saved));
            categoryRepo.saveAll(cats);
        }
        return saved;
    }

    @MutationMapping
    @Transactional
    public ProductEntity updateProduct(@Argument Long id, @Argument ProductInput input) {
        ProductEntity p = productRepo.findById(id).orElseThrow();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        if (input.ownerId()!=null) {
            UserEntity owner = userRepo.findById(input.ownerId()).orElse(null);
            p.setOwner(owner);
        }
        if (input.categoryIds()!=null) {
            Set<CategoriesEntity> newCats = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            categoryRepo.findAll().forEach(c -> c.getProducts().remove(p));
            newCats.forEach(c -> c.getProducts().add(p));
            categoryRepo.saveAll(newCats);
        }
        return productRepo.save(p);
    }

    @MutationMapping
    @Transactional
    public Boolean deleteProduct(@Argument Long id) {
        if (!productRepo.existsById(id)) return false;
        productRepo.deleteById(id);
        return true;
    }

    @MutationMapping
    @Transactional
    public CategoriesEntity createCategory(@Argument CategoryInput input) {
        CategoriesEntity c = new CategoriesEntity();
        c.setName(input.name());
        c.setImages(input.images());
        CategoriesEntity saved = categoryRepo.save(c);

        if (input.productIds()!=null && !input.productIds().isEmpty()) {
            Set<ProductEntity> ps = new HashSet<>(productRepo.findAllById(input.productIds()));
            ps.forEach(p -> p.setCategory(saved));
            productRepo.saveAll(ps);
        }
        if (input.userIds()!=null && !input.userIds().isEmpty()) {
            Set<UserEntity> us = new HashSet<>(userRepo.findAllById(input.userIds()));
            us.forEach(u -> u.getCategories().add(saved));
            userRepo.saveAll(us);
        }
        return saved;
    }

    @MutationMapping
    @Transactional
    public CategoriesEntity updateCategory(@Argument Long id, @Argument CategoryInput input) {
        CategoriesEntity c = categoryRepo.findById(id).orElseThrow();
        c.setName(input.name());
        c.setImages(input.images());

        if (input.productIds()!=null) {
            // Remove this category from all products first
            Set<ProductEntity> allProducts = new HashSet<>(productRepo.findAll());
            allProducts.forEach(p -> {
                if (p.getCategory() != null && p.getCategory().getId().equals(c.getId())) {
                    p.setCategory(null);
                }
            });
            productRepo.saveAll(allProducts);
            // Set this category for selected products
            Set<ProductEntity> ps = new HashSet<>(productRepo.findAllById(input.productIds()));
            ps.forEach(p -> p.setCategory(c));
            productRepo.saveAll(ps);
        }
        if (input.userIds()!=null) {
            c.getUsers().clear();
            Set<UserEntity> us = new HashSet<>(userRepo.findAllById(input.userIds()));
            c.getUsers().addAll(us);
        }
        return categoryRepo.save(c);
    }

    @MutationMapping
    @Transactional
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }
}