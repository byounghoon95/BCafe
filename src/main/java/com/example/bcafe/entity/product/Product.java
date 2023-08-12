package com.example.bcafe.entity.product;

import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import com.example.bcafe.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_code", unique = true)
    private String productCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

    @Builder
    private Product(String productCode, String name, Integer price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public Product updateProduct(ProductUpdateServiceRequest request) {
        if (request.getName() != null) this.name = request.getName();
        if (request.getPrice() != 0) this.price = request.getPrice();
        return this;
    }
}