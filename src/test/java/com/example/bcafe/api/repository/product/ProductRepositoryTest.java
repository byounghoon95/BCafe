package com.example.bcafe.api.repository.product;

import com.example.bcafe.entity.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product createProduct(String productCode, String name, int price) {
        return Product.builder()
                .productCode(productCode)
                .name(name)
                .price(price)
                .build();
    }

    private void createProducts() {
        Product product1 = createProduct("P00001",  "아메리카노", 4000);
        Product product2 = createProduct("P00002",  "카페라떼", 6000);
        Product product3 = createProduct("P00003",  "녹차라떼", 6400);
        productRepository.saveAll(List.of(product1,product2,product3));
    }

    @DisplayName("가장 마지막에 등록된 상품의 코드를 조회한다")
    @Test
    void find_latest_product_code() {
        // given
        createProducts();

        // when
        String latestProductCode = productRepository.findLatestProductCode();

        // then
        assertThat(latestProductCode).isEqualTo("P00003");
    }

    @DisplayName("상품 코드로 하나의 상품을 조회한다")
    @Test
    void find_by_product_code() {
        // given
        createProducts();

        // when
        Product findProduct = productRepository.findByProductCode("P00001").get();

        // then
        assertThat(findProduct).isNotNull();
        assertThat(findProduct.getProductCode()).isEqualTo("P00001");
    }

}