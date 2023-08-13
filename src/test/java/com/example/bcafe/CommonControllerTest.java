package com.example.bcafe;

import com.example.bcafe.api.controller.member.MemberApiController;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.controller.member.request.MemberDeleteRequest;
import com.example.bcafe.api.controller.order.OrderApiController;
import com.example.bcafe.api.controller.order.request.OrderCreateRequest;
import com.example.bcafe.api.controller.product.ProductApiController;
import com.example.bcafe.api.controller.product.request.ProductCreateRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.order.OrderService;
import com.example.bcafe.api.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ActiveProfiles("test")
@WebMvcTest(controllers = {
        ProductApiController.class,
        MemberApiController.class,
        OrderApiController.class,
})
public abstract class CommonControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected ProductService productService;
    @MockBean
    protected MemberService memberService;
    @MockBean
    protected OrderService orderService;

    protected ProductCreateRequest productCreateRequest() {
        return ProductCreateRequest.builder()
                .name("아메리카노")
                .price(5000)
                .build();
    }

    protected ProductCreateRequest productCreateNoNameRequest() {
        return ProductCreateRequest.builder()
                .price(5000)
                .build();
    }

    protected ProductCreateRequest productCreate0PriceRequest() {
        return ProductCreateRequest.builder()
                .name("아메리카노")
                .price(0)
                .build();
    }

    protected OrderCreateRequest orderCreateRequest() {
        return OrderCreateRequest.builder()
                .productCodes(List.of("P00001"))
                .phoneNumber("010-1111-2222")
                .build();
    }

    protected MemberCreateRequest memberCreateRequest() {
        return MemberCreateRequest.builder()
                .phoneNumber("010-1111-2222")
                .name("이병훈")
                .age(29)
                .build();
    }

    protected MemberDeleteRequest memberDeleteRequest() {
        return MemberDeleteRequest.builder()
                .phoneNumber("010-1111-2222")
                .build();
    }
}
