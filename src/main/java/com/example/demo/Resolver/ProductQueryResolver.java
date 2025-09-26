package com.example.demo.Resolver;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ProductService;
import com.example.demo.Repository.ProductRepository;
@Component
public class ProductQueryResolver {

	 private final ProductRepository productRepository;

	    public ProductQueryResolver(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }

	    @QueryMapping
	    public List<ProductEntity> productsByPriceAsc() {
	        return productRepository.findAllByOrderByPriceAsc();
	    }

	    @QueryMapping
	    public List<ProductEntity> productsByCategory(@Argument("categoryId") Long categoryId) {
	        return productRepository.findByCategoryId(categoryId);
	    }

	    @QueryMapping
	    public List<ProductEntity> products() {
	        return productRepository.findAll();
	    }

	    @QueryMapping
	    public ProductEntity product(@Argument("id") Long id) {
	        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	    }
}