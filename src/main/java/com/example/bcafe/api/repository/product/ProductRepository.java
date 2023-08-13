package com.example.bcafe.api.repository.product;

import com.example.bcafe.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p.product_code from product p order by id desc limit 1", nativeQuery = true)
    String findLatestProductCode();
    Optional<Product> findByProductCode(String productCode);
    List<Product> findAllByProductCodeIn(List<String> ProductCodeList);

}
