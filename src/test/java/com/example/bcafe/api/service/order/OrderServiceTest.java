package com.example.bcafe.api.service.order;

import com.example.bcafe.api.repository.member.MemberRepository;
import com.example.bcafe.api.repository.product.ProductRepository;
import com.example.bcafe.api.repository.stock.StockRepository;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.order.response.OrderResponse;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.product.Product;
import com.example.bcafe.entity.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Product createProduct(String productCode, int price) {
        return Product.builder()
                .productCode(productCode)
                .price(price)
                .name("메뉴 이름")
                .build();
    }

    private Stock createStock(String productCode, int quantity) {
        return Stock.builder()
                .productCode(productCode)
                .quantity(quantity)
                .build();
    }

    private Member createMember(String phoneNumber, String name, int age) {
        return Member.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .age(age)
                .build();
    }

    private void setUp() {
        Product product1 = createProduct("P00001",1000);
        Product product2 = createProduct("P00002",3000);
        Product product3 = createProduct("P00003",5000);
        Product product4 = createProduct("P00004",200000);
        Product product5 = createProduct("P00005",500000);
        Product product6 = createProduct("P00006",1000000);
        productRepository.saveAll(List.of(product1, product2, product3, product4, product5, product6));

        Stock stock1 = createStock("P00001", 4);
        Stock stock2 = createStock("P00002", 4);
        Stock stock3 = createStock("P00003", 10);
        Stock stock4 = createStock("P00004", 10);
        Stock stock5 = createStock("P00005", 10);
        Stock stock6 = createStock("P00006", 10);
        stockRepository.saveAll(List.of(stock1, stock2, stock3, stock4, stock5, stock6));

        Member member = createMember("010-1111-2222", "이병훈", 29);
        memberRepository.save(member);
    }

    @DisplayName("주문코드 리스트를 받아 주문을 생성한다")
    @Test
    void create_order() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        setUp();
        OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00002"))
                .phoneNumber("010-1111-2222")
                .build();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);
        System.out.println("orderResponse : " + orderResponse);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getMember().getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 4000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 1000),
                        tuple("P00002", 3000)
                );
    }
}