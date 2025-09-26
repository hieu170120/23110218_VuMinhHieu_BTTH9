package com.example.demo.Graph;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ProductService;
import com.example.demo.DTO.ProductInput;

import java.util.List;

@Component
public class GraphqlExceptionResolver {

    private final ProductService productService;

    public GraphqlExceptionResolver(ProductService productService) {
        this.productService = productService;
    }

	/*
	 * @QueryMapping public List<ProductEntity> productsByPriceAsc() { return
	 * productService.getProductsSortedByPriceAsc(); }
	 * 
	 * @QueryMapping public List<Product> productsByCategory(@Argument("categoryId")
	 * Long categoryId) { return productService.getProductsByCategory(categoryId); }
	 * 
	 * @MutationMapping public Product createProduct(@Argument("input") ProductInput
	 * input) { return productService.createProduct(input); }
	 */
}
