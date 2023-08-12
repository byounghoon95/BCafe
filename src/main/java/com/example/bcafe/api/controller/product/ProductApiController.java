package com.example.bcafe.api.controller.product;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.product.request.ProductCreateRequest;
import com.example.bcafe.api.controller.product.request.ProductUpdateRequest;
import com.example.bcafe.api.service.product.ProductService;
import com.example.bcafe.api.service.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/create")
    public CommonResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return new CommonResponse<>(productService.createProduct(request.toServiceRequest()));
    }

    @PostMapping("/update/{productCode}")
    private CommonResponse<ProductResponse> updateProduct(
            @Valid @RequestBody ProductUpdateRequest request,
            @PathVariable String productCode
    ) {
        return new CommonResponse<>(productService.updateProduct(productCode, request.toServiceRequest()));
    }

}
