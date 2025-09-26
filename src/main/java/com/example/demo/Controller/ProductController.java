package com.example.demo.Controller;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.DTO.ProductInput;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Entity.CategoriesEntity;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.CategoryRepository;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/sorted-by-price")
    public List<ProductEntity> getAllProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductInput input) {
        ProductEntity product = new ProductEntity();
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setDesc(input.desc());
        product.setPrice(input.price());
        if (input.ownerId() != null) {
            Optional<UserEntity> owner = userRepository.findById(input.ownerId());
            owner.ifPresent(product::setOwner);
        }
        if (input.categoryIds() != null) {
            Set<CategoriesEntity> categories = new HashSet<>(categoryRepository.findAllById(input.categoryIds()));
            product.setCategories(categories);
        }
        ProductEntity saved = productRepository.save(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductInput input) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return ResponseEntity.notFound().build();
        ProductEntity product = optionalProduct.get();
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setDesc(input.desc());
        product.setPrice(input.price());
        if (input.ownerId() != null) {
            Optional<UserEntity> owner = userRepository.findById(input.ownerId());
            owner.ifPresent(product::setOwner);
        }
        if (input.categoryIds() != null) {
            Set<CategoriesEntity> categories = new HashSet<>(categoryRepository.findAllById(input.categoryIds()));
            product.setCategories(categories);
        }
        ProductEntity saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) return ResponseEntity.notFound().build();
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}