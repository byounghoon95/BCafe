package com.example.bcafe.api.service.product;

import com.example.bcafe.CommonServiceTest;
import com.example.bcafe.api.service.product.request.ProductCreateServiceRequest;
import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import com.example.bcafe.api.service.product.response.ProductResponse;
import com.example.bcafe.api.service.product.response.ProductUpdateResponse;
import com.example.bcafe.entity.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class ProductServiceTest extends CommonServiceTest {

    @DisplayName("신규 상품을 등록한다. 상품코드는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    @Test
    void create_product() {
        // given
        Product product = createProduct("P00001", "아메리카노", 4000);
        productRepository.save(product);

        // when
        ProductCreateServiceRequest request = ProductCreateServiceRequest.builder()
                .name("카페라떼")
                .price(6000)
                .build();
        productService.createProduct(request);

        // then
        List<Product> products = productRepository.findAll();
        assertThat(products)
                .hasSize(2)
                .extracting("productCode","name", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", "아메리카노", 4000),
                        tuple("P00002", "카페라떼", 6000)
                );
    }

    @DisplayName("신규 상품을 등록한다. 등록된 상품이 없으면 P00001을 반환한다")
    @Test
    void create_product_with_0_product() {
        // given
        ProductCreateServiceRequest request = ProductCreateServiceRequest.builder()
                .name("카페라떼")
                .price(6000)
                .build();

        // when
        ProductResponse response = productService.createProduct(request);

        //then
        assertThat(response.getProductCode()).isEqualTo( "P00001");
    }

    private static Stream<Arguments> nextNumber() {
        return Stream.of(
                Arguments.of("00009", "00010"),
                Arguments.of("00099", "00100"),
                Arguments.of("00999", "01000"),
                Arguments.of("09999", "10000")
        );
    }

    @DisplayName("신규 상품을 등록한다. 최근 등록된 상품 번호가 *9라면 다음 숫자로 넘어가등록한다")
    @MethodSource("nextNumber")
    @ParameterizedTest
    void create_product_at_999(String prevNumber, String nextNumber) {
        // given
        Product request = Product.builder()
                .productCode("P" + prevNumber)
                .name("카푸치노")
                .price(5500)
                .build();
        productRepository.save(request);

        // when
        ProductCreateServiceRequest serviceRequest = ProductCreateServiceRequest.builder()
                .name("카페라떼")
                .price(6000)
                .build();
        ProductResponse response = productService.createProduct(serviceRequest);

        //then
        assertThat(response.getProductCode()).isEqualTo("P" + nextNumber);
    }

    @DisplayName("등록된 상품의 정보를 수정한다")
    @Test
    void update_product() {
        // given
        setUp();
        ProductUpdateServiceRequest serviceRequest = ProductUpdateServiceRequest.builder()
                .name("아메리카노1")
                .price(8000)
                .quantity(8)
                .build();

        // when
        ProductUpdateResponse response = productService.updateProduct("P00001", serviceRequest);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getProductCode()).isEqualTo("P00001");
        assertThat(response.getName()).isEqualTo("아메리카노1");
        assertThat(response.getPrice()).isEqualTo(8000);
        assertThat(response.getQuantity()).isEqualTo(8);
    }
}