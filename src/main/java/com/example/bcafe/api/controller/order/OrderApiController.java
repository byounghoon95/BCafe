package com.example.bcafe.api.controller.order;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.order.request.OrderCreateRequest;
import com.example.bcafe.api.service.order.OrderService;
import com.example.bcafe.api.service.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequestMapping("/api/order")
@RequiredArgsConstructor
@RestController
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/create")
    public CommonResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return new CommonResponse<>(orderService.createOrder(request.toServiceRequest(), LocalDateTime.now()));
    }
}
