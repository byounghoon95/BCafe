package com.example.bcafe.api.repository.stock;

import com.example.bcafe.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.productCode IN :productcodes")
    List<Stock> findAllByProductCodeIn(List<String> productcodes);
    Stock findByProductCode(String productCode);
}
