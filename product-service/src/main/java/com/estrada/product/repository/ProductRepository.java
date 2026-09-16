package com.estrada.product.repository;

import com.estrada.product.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

}
