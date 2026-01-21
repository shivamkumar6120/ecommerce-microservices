package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public order info");
    }

    @GetMapping("/my")
    public ResponseEntity<String> myOrders(Authentication authentication) {

        String userEmail = authentication.getName();

        return ResponseEntity.ok("Orders for user: " + userEmail);
    }
    
    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(
            @RequestHeader("Authorization") String authHeader,
            Authentication authentication) {

        String token = authHeader.substring(7);
        String userEmail = authentication.getName();

        return ResponseEntity.ok(
                orderService.placeOrder(token, userEmail)
        );
    }

    
}
