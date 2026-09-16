package com.estrada.order.controllers;

import com.estrada.order.model.dto.OrderRequest;
import com.estrada.order.model.dto.OrderResponse;
import com.estrada.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return this.orderService.placeOrder(orderRequest)
                .thenReturn("Order placed successfully");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<OrderResponse>> getAllOrders() {
        return this.orderService.getAllOrders();
    }
}
