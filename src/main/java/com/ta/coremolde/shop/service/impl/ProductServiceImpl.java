package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import com.ta.coremolde.shop.repository.ProductRepository;
import com.ta.coremolde.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        return productRepository.save(Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .weight(productRequest.getWeight())
                .rating(0)
                .stock(productRequest.getStock())
                .build());
    }

    @Override
    public Product updateProduct(Integer productId, ProductRequest productRequest) {
        checkProductExistance(productId);

        Product product = productRepository.findProductById(productId);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setWeight(productRequest.getWeight());
        product.setStock(productRequest.getStock());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Integer productId) {
        checkProductExistance(productId);

        productRepository.deleteById(productId);
        return ResponseConstant.DELETE_PRODUCT_SUCCESS;
    }

    private void checkProductExistance(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new MoldeException(
                ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

}
