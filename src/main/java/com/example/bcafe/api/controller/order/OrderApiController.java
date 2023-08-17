package com.example.bcafe.api.controller.order;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.order.request.OrderCreateRequest;
import com.example.bcafe.api.service.order.OrderService;
import com.example.bcafe.api.service.order.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Api(tags = "주문 API")
@RequestMapping("/api/order")
@RequiredArgsConstructor
@RestController
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/create")
    @ApiOperation(value = "주문 생성", notes = "핸드폰 번호와 상품번호 리스트를 갖고 주문을 생성한다")
    public CommonResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return new CommonResponse<>(orderService.createOrder(request.toServiceRequest(), LocalDateTime.now()));
    }
}
