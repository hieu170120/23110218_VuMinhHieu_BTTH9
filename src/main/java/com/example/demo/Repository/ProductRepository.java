package com.example.demo.Repository;
import com.example.demo.Entity.ProductEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByCategoryIdOrderByPriceAsc(Long categoryId);
	List<ProductEntity> findAllByOrderByPriceAsc();
	List<ProductEntity> findByCategoryId(Long categoryId);
}