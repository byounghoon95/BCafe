package com.example.bcafe.api.repository.stock;

import com.example.bcafe.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProductCodeIn(List<String> codes);
    Stock findByProductCode(String productCode);
}
