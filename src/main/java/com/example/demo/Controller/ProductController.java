package com.example.demo.Controller;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/sorted-by-price")
    public List<ProductEntity> getAllProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }
}
