package com.estrada.product.controller;

import com.estrada.product.model.dto.ProductRequest;
import com.estrada.product.model.dto.ProductResponse;
import com.estrada.product.model.entities.Product;
import com.estrada.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductController  {

    @Autowired
    private IProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addProduct(@RequestBody ProductRequest productRequest) {
        this.productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }

}
