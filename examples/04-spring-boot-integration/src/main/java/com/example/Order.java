package com.example;

import java.time.Instant;

public record Order(long orderId, String customerName, Instant createdAt) {
}
