package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CartResponse;
import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.util.ServiceUrls;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;

    public String placeOrder(String token, String userEmail) {

        // 1️⃣ Call Cart Service
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        CartResponse cartResponse = restTemplate
                .exchange(
                        ServiceUrls.CART_SERVICE,
                        org.springframework.http.HttpMethod.GET,
                        entity,
                        CartResponse.class
                )
                .getBody();

        if (cartResponse == null || cartResponse.getCart().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        List<String> cartItems = cartResponse.getCart();

        // 2️⃣ (Mock) Calculate total amount
        double totalAmount = cartItems.size() * 1000;

        // 3️⃣ Call Payment Service (.NET)
        PaymentRequest paymentRequest = new PaymentRequest(totalAmount);

        PaymentResponse paymentResponse = restTemplate
                .postForObject(
                        ServiceUrls.PAYMENT_SERVICE + "/pay",
                        paymentRequest,
                        PaymentResponse.class
                );

        if (paymentResponse == null || !"SUCCESS".equals(paymentResponse.getStatus())) {
            throw new RuntimeException("Payment failed");
        }

        // 4️⃣ Order confirmed
        return "Order placed successfully for user " + userEmail +
               " | Items: " + cartItems +
               " | Amount: " + totalAmount;
    }
}
