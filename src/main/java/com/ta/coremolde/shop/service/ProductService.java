package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;

public interface ProductService {

    Product addProduct(ProductRequest productRequest);
    Product updateProduct(Integer productId, ProductRequest productRequest);
    String deleteProduct(Integer productId);

}
