package com.example.bcafe;

import com.example.bcafe.api.controller.member.MemberApiController;
import com.example.bcafe.api.controller.product.ProductApiController;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(controllers = {
        ProductApiController.class,
        MemberApiController.class,
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
}
