package com.example.demo.Resolver;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import java.util.List;

@Component
public class ProductResolver {
    @Autowired
    private ProductRepository productRepository;

    @QueryMapping
    public List<ProductEntity> allProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }
}
