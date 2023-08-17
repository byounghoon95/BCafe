package com.example.bcafe.api.controller.product;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.product.request.ProductCreateRequest;
import com.example.bcafe.api.controller.product.request.ProductUpdateRequest;
import com.example.bcafe.api.service.product.ProductService;
import com.example.bcafe.api.service.product.response.ProductResponse;
import com.example.bcafe.api.service.product.response.ProductUpdateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "상품 API")
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/create")
    @ApiOperation(value = "상품 등록", notes = "상품 이름, 가격, 재고 정보로 상품을 등록한다")
    public CommonResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return new CommonResponse<>(productService.createProduct(request.toServiceRequest()));
    }

    @PostMapping("/update/{productCode}")
    @ApiOperation(value = "상품 수정", notes = "상품 이름, 가격, 재고 정보로 상품정보를 수정한다")
    private CommonResponse<ProductUpdateResponse> updateProduct(
            @Valid @RequestBody ProductUpdateRequest request,
            @PathVariable String productCode
    ) {
        return new CommonResponse<>(productService.updateProduct(productCode, request.toServiceRequest()));
    }

}
