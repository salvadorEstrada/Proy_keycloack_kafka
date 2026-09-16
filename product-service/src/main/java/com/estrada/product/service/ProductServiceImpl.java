package com.estrada.product.service;

import com.estrada.product.model.dto.ProductRequest;
import com.estrada.product.model.dto.ProductResponse;
import com.estrada.product.model.entities.Product;
import com.estrada.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void addProduct(ProductRequest productRequest) {
        var product = Product.builder()
                //.id(productRequest.getId())
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.isStatus())
                .build();
        productRepository.save(product);
        log.info("Product add {}", product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();

        return products.stream().map(this:: mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.isStatus())
                .build();
    }


}
