package com.example.bcafe.api.repository.product;

import com.example.bcafe.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p.product_code from product p order by id desc limit 1", nativeQuery = true)
    String findLatestProductCode();
}
