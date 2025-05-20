package com.data.repository;

import com.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: danh dau day la 1 repository
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
