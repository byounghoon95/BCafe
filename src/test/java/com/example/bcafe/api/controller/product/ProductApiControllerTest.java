package com.example.bcafe.api.controller.product;

import com.example.bcafe.CommonControllerTest;
import com.example.bcafe.api.controller.product.request.ProductCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ProductApiControllerTest extends CommonControllerTest {

    private ProductCreateRequest createRequest() {
        return ProductCreateRequest.builder()
                .name("아메리카노")
                .price(5000)
                .build();
    }

    @DisplayName("신규 상품을 등록한다")
    @Test
    void create_product() throws Exception {
        //given
        ProductCreateRequest request = createRequest();
        //when //then
        mockMvc.perform(post("/api/product/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0000"));
    }

    @DisplayName("신규 상품을 등록할 때 상품 이름은 필수값이다")
    @Test
    void create_product_without_name() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .price(5000)
                .build();

        // when // then
        mockMvc.perform(post("/api/product/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("9999"))
                .andExpect(jsonPath("$.message").value("상품 이름은 필수입니다"))
        ;
    }

    @DisplayName("신규 상품을 등록할 때 상품 가격은 양수이다")
    @Test
    void create_product_price_with_not_positive() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .name("아메리카노")
                .price(0)
                .build();

        // when // then
        mockMvc.perform(post("/api/product/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("9999"))
                .andExpect(jsonPath("$.message").value("상품 가격은 양수여야 합니다"))
        ;
    }

    @DisplayName("등록된 상품을 수정한다")
    @Test
    void update_product() throws Exception {
        //given
        ProductCreateRequest request = createRequest();
        String productCode = "P00001";

        //when //then
        mockMvc.perform(post("/api/product/update/" + productCode)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0000"));
    }

}