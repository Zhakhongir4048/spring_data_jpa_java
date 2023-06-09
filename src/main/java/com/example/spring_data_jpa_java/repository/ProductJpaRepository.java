package com.example.spring_data_jpa_java.repository;

import com.example.spring_data_jpa_java.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}