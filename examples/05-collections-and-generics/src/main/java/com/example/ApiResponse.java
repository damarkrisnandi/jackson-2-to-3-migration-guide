package com.example;

public record ApiResponse<T>(boolean ok, T data) {
}
