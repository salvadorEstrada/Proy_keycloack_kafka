package com.estrada.order.service;

import com.estrada.order.model.dto.OrderRequest;
import com.estrada.order.model.dto.OrderResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IOrderService {

  Mono<Object> placeOrder(OrderRequest orderRequest);

  Mono<List<OrderResponse>> getAllOrders();
}
