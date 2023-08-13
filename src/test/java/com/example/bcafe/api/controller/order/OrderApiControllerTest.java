package com.example.bcafe.api.controller.order;

import com.example.bcafe.CommonControllerTest;
import com.example.bcafe.api.controller.order.request.OrderCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class OrderApiControllerTest extends CommonControllerTest {
    @DisplayName("신규 주문을 등록한다")
    @Test
    void create_order() throws Exception {
        // given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .productCodes(List.of("P00001"))
                .phoneNumber("010-1111-2222")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/order/create")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value("0000"))
        ;
    }

}