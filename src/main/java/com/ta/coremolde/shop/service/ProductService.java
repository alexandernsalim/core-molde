package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(Integer id);

    Product addProduct(Integer shopId, ProductRequest productRequest, MultipartFile image);

    Product updateProduct(Integer productId, ProductRequest productRequest, MultipartFile image);

    String deleteProduct(Integer productId);

    Product decreaseStock(Product product, int qty);

}
