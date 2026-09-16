package com.estrada.order.model.dto;

public record OrderItemsResponse(Long id, String sku, double price, Long quantity) {
}
