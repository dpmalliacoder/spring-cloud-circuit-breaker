package com.spring.cloud.circuit.breaker.repository;

import com.spring.cloud.circuit.breaker.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
