package com.example.bcafe.api.service.product;

import com.example.bcafe.api.repository.product.ProductRepository;
import com.example.bcafe.api.service.product.request.ProductCreateServiceRequest;
import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import com.example.bcafe.api.service.product.response.ProductResponse;
import com.example.bcafe.entity.product.Product;
import com.example.bcafe.enums.CodeEnum;
import com.example.bcafe.exception.CustomException;
import com.example.bcafe.utils.CodeGenerateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = CodeGenerateUtil.createNextCode(productRepository.findLatestProductCode(),"P");

        Product savedProduct = productRepository.save(request.toProductEntity(nextProductNumber));

        return ProductResponse.of(savedProduct);
    }

    @Transactional
    public ProductResponse updateProduct(String productCode, ProductUpdateServiceRequest request) {
        Product findProduct = productRepository.findByProductCode(productCode).orElseThrow(() -> new CustomException(CodeEnum.PRODUCT_NOTFOUND));
        findProduct.updateProduct(request);

        return ProductResponse.of(findProduct);
    }

}