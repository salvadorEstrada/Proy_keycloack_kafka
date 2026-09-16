package com.estrada.inventory.controller;

import com.estrada.inventory.model.dto.BaseResponse;
import com.estrada.inventory.model.dto.OrderItemRequest;
import com.estrada.inventory.service.IServiceInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")

public class InventoryController {

    @Autowired
    private final IServiceInventory serviceInventory;

    public InventoryController(IServiceInventory serviceInventory) {
        this.serviceInventory = serviceInventory;
    }



    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)

    public boolean isInStock(@PathVariable("sku") String sku){
        return serviceInventory.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems){
        return serviceInventory.areInStock(orderItems);
    }

}
