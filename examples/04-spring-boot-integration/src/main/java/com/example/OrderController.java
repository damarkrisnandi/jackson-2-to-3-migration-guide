package com.example;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/orders/1")
    public Order get() {
        return new Order(1L, "Ada", Instant.parse("2025-01-01T10:00:00Z"));
    }

    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        return order;
    }
}
