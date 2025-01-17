package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(Integer id);

    Product addProduct(Integer shopId, ProductRequest productRequest, MultipartFile image);

    Product updateProduct(Integer productId, ProductRequest productRequest, MultipartFile image);

    String deleteProduct(Integer productId);

    Product updateStock(Product product, int qty);

    List<ReviewResponse> getProductReviews(Integer productId);

}
