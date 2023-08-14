package com.example.bcafe.api.service.order;

import com.example.bcafe.api.repository.member.MemberRepository;
import com.example.bcafe.api.repository.order.OrderRepository;
import com.example.bcafe.api.repository.points.PointsRepository;
import com.example.bcafe.api.repository.product.ProductRepository;
import com.example.bcafe.api.repository.stock.StockRepository;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.order.response.OrderResponse;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.order.Order;
import com.example.bcafe.entity.points.Points;
import com.example.bcafe.entity.product.Product;
import com.example.bcafe.entity.stock.Stock;
import com.example.bcafe.enums.CodeEnum;
import com.example.bcafe.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final StockRepository stockRepository;
    private final PointsRepository pointsRepository;

    @Transactional
    public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) {
        List<String> productCodes = request.getProductCodes();
        List<Product> products = findProductsBy(productCodes);

        Member member = memberRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new CustomException(CodeEnum.MEMBER_NOTFOUND));

        deductProductStockQuantities(products);

        Order order = Order.create(products, member, registeredDateTime);

        plusPoints(order,member);
        return OrderResponse.of(orderRepository.save(order));
    }

    private void deductProductStockQuantities(List<Product> products) {
        List<String> stockProductCodes = extractStockProductCodes(products);

        Map<String, Stock> stockMap = stockMapBy(stockProductCodes);
        Map<String, Long> productCountingMap = countingMapBy(stockProductCodes);

        for (String stockProductCode : new HashSet<>(stockProductCodes)) {
            Stock stock = stockMap.get(stockProductCode);
            int quantity = productCountingMap.get(stockProductCode).intValue();

            if (stock.quantityLessThan(quantity)) {
                throw new CustomException(CodeEnum.LESS_STOCK);
            }
            stock.deductQuantity(quantity);
        }
    }

    private List<Product> findProductsBy(List<String> productCodes) {
        List<Product> products = productRepository.findAllByProductCodeIn(productCodes);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductCode, p -> p));

        return productCodes.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }

    private static List<String> extractStockProductCodes(List<Product> products) {
        return products.stream()
                .map(Product::getProductCode)
                .collect(Collectors.toList());
    }

    private Map<String, Stock> stockMapBy(List<String> stockProductCodes) {
        List<Stock> stocks = stockRepository.findAllByProductCodeIn(stockProductCodes);
        return stocks.stream()
                .collect(Collectors.toMap(Stock::getProductCode, s -> s));
    }

    private Map<String, Long> countingMapBy(List<String> stockProductCodes) {
        return stockProductCodes.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    private void plusPoints(Order order, Member member) {
        if (!member.getPhoneNumber().equals("000-0000-0000")) {
            Points points = pointsRepository.findByPhoneNumber(member.getPhoneNumber()).orElseThrow(() -> new CustomException(CodeEnum.POINTS_NOTFOUND));
            int plusAmount = points.getAmount() + (int) (order.getTotalPrice() * 0.03);
            points.updatePoints(plusAmount);
        }
    }
}
