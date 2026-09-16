package com.estrada.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    //private Long id;
    private String sku;
    private String name;
    private String description;
    private double price;
    private boolean status;

}
