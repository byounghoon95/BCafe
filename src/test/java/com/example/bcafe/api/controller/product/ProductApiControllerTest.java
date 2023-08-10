package com.example.bcafe.api.controller.product;

import com.example.bcafe.CommonControllerTest;
import com.example.bcafe.api.controller.product.request.ProductCreateRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ProductApiControllerTest extends CommonControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProductService productService;
    @MockBean
    MemberService memberService;
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

    @DisplayName("신규 상품을 등록할 때 재고는 0 이상이다")
    @Test
    void create_product_stock_more_than_0() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .name("아메리카노")
                .price(5000)
                .quantity(-1)
                .build();

        // when // then
        mockMvc.perform(post("/api/product/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("9999"))
                .andExpect(jsonPath("$.message").value("상품 재고는 0개 이상입니다"))
        ;
    }
}