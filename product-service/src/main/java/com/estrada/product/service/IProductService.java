package com.estrada.product.service;

import com.estrada.product.model.dto.ProductRequest;
import com.estrada.product.model.dto.ProductResponse;
import com.estrada.product.model.entities.Product;

import java.util.List;

public interface IProductService {

    void addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();


}
