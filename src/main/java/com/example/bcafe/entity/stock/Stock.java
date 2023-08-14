package com.example.bcafe.entity.stock;

import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import com.example.bcafe.entity.BaseEntity;
import com.example.bcafe.enums.CodeEnum;
import com.example.bcafe.exception.CustomException;
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

    @Column(name = "product_code", unique = true)
    private String productCode;

    private int quantity;

    @Builder
    private Stock(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public void updateQuantity(ProductUpdateServiceRequest request) {
        this.quantity = request.getQuantity();
    }

    public boolean quantityLessThan(int quantity) {
        return this.quantity < quantity;
    }

    public void deductQuantity(int quantity) {
        if (quantityLessThan(quantity)) {
            throw new CustomException(CodeEnum.LESS_STOCK);
        }
        this.quantity -= quantity;
    }
}
