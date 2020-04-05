package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(Integer id);

    Product addProduct(ProductRequest productRequest);

    Product updateProduct(Integer productId, ProductRequest productRequest);

    String deleteProduct(Integer productId);

}
