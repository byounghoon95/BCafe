package com.example.bcafe.entity.stock;

import com.example.bcafe.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stock")
@Entity
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;

    private int quantity;

    @Builder
    private Stock(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

}
