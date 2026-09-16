package com.estrada.inventory.service;

import com.estrada.inventory.model.dto.BaseResponse;
import com.estrada.inventory.model.dto.OrderItemRequest;

import java.util.List;

public interface IServiceInventory {

    public boolean isInStock(String sku);

    public BaseResponse areInStock(List<OrderItemRequest> orderItems);


}
