package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(Integer id);

    boolean existsById(Integer id);

}
