package com.estrada.order.service;

import com.estrada.order.model.dto.*;
import com.estrada.order.model.entities.Order;
import com.estrada.order.model.entities.OrderItems;
import com.estrada.order.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Object> placeOrder(OrderRequest orderRequest) {
        return this.webClientBuilder.build()
                .post()
                .uri("lb://msvc-inventory/api/v1/inventories/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .flatMap(result -> {
                    if (result != null && !result.hasErrors()) {
                        Order order = new Order();
                        order.setOrderNumber(UUID.randomUUID().toString());
                        order.setOrderItems(orderRequest.getOrderItems().stream()
                                .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                                .toList());
                        var saveOrder = this.orderRepository.save(order);
                        return Mono.just(this.mapToOrderResponse(saveOrder));

                    } else {
                        return Mono.error(new IllegalArgumentException("One or more items are not in stock"));
                    }
                });
    }

    @Override
    public Mono<List<OrderResponse>> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        return Mono.just(orders.stream().map(this::mapToOrderResponse).toList());
    }


    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderItems().stream().map(this::mapToOrderItemsResponse).toList());
    }

    private OrderItemsResponse mapToOrderItemsResponse(OrderItems orderItems) {
        return new OrderItemsResponse(orderItems.getId(), orderItems.getSku(), orderItems.getPrice(), orderItems.getQuantity());
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .quantity(orderItemRequest.getQuantity())
                .price(orderItemRequest.getPrice())
                .order(order)
                .build();
    }
}

