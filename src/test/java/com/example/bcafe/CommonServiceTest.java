package com.example.bcafe;

import com.example.bcafe.api.repository.member.MemberRepository;
import com.example.bcafe.api.repository.points.PointsRepository;
import com.example.bcafe.api.repository.product.ProductRepository;
import com.example.bcafe.api.repository.stock.StockRepository;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import com.example.bcafe.api.service.member.request.MemberDeleteServiceRequest;
import com.example.bcafe.api.service.order.OrderService;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.product.ProductService;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.points.Points;
import com.example.bcafe.entity.product.Product;
import com.example.bcafe.entity.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public abstract class CommonServiceTest {
    @Autowired
    protected MemberService memberService;
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected OrderService orderService;
    @Autowired
    protected StockRepository stockRepository;
    @Autowired
    protected PointsRepository pointsRepository;

    protected MemberCreateServiceRequest memberCreateServiceRequest() {
        return MemberCreateServiceRequest.builder()
                .phoneNumber("010-1111-2222")
                .name("이병훈")
                .age(29)
                .build();
    }
    protected MemberDeleteServiceRequest memberDeleteServiceRequest() {
        return MemberDeleteServiceRequest.builder()
                .phoneNumber("010-1111-2222")
                .build();
    }
    protected OrderCreateServiceRequest orderCreateServiceMemberRequest() {
        return OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00002"))
                .phoneNumber("010-1111-2222")
                .build();
    }

    protected OrderCreateServiceRequest orderCreateServiceNotMemberRequest() {
        return OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00002"))
                .phoneNumber("000-0000-0000")
                .build();
    }

    protected Product createProduct(String productCode, String name, int price) {
        return Product.builder()
                .productCode(productCode)
                .name(name)
                .price(price)
                .build();
    }

    protected Stock createStock(String productCode, int quantity) {
        return Stock.builder()
                .productCode(productCode)
                .quantity(quantity)
                .build();
    }
    protected Member createMember(String phoneNumber, String name, int age) {
        return Member.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .age(age)
                .build();
    }

    protected Points createPoints(String phoneNumber) {
        return Points.builder()
                .phoneNumber(phoneNumber)
                .amount(0)
                .build();
    }

    protected void setUpProduct() {
        Product product1 = createProduct("P00001", "아메리카노", 4000);
        Product product2 = createProduct("P00002","카페라떼",6000);
        Product product3 = createProduct("P00003", "녹차라떼", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));
    }

    protected void setUpMember() {
        Member member1 = createMember("010-1111-2222", "이병훈", 29);
        Member member2 = createMember("000-0000-0000", "비회원", 29);
        memberRepository.saveAll(List.of(member1, member2));
    }

    protected void setUpStock() {
        Stock stock1 = createStock("P00001", 5);
        Stock stock2 = createStock("P00002", 5);
        Stock stock3 = createStock("P00003", 5);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));
    }

    protected void setUpPoints() {
        Points points1 = createPoints("010-1111-2222");
        Points points2 = createPoints("000-0000-0000");
        pointsRepository.saveAll(List.of(points1, points2));
    }

    protected void setUp() {
        setUpProduct();
        setUpMember();
        setUpStock();
        setUpPoints();
    }


}
