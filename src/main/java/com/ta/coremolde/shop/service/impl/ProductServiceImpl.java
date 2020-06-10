package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import com.ta.coremolde.shop.repository.ProductRepository;
import com.ta.coremolde.shop.service.ProductService;
import com.ta.coremolde.shop.service.ReviewService;
import com.ta.coremolde.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShopUserService shopUserService;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product addProduct(Integer shopId, ProductRequest productRequest, MultipartFile image) {
        storageService.saveImage(shopId, image);

        String filename = "/molde/api/v1/storage/product/shop-" + shopId + "-" + image.getOriginalFilename();

        return productRepository.save(Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .weight(productRequest.getWeight())
                .rating(0)
                .stock(productRequest.getStock())
                .image(filename)
                .build());
    }

    @Override
    public Product updateProduct(Integer productId, ProductRequest productRequest, MultipartFile image) {
        checkProductExistence(productId);

        Product product = productRepository.findProductById(productId);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setWeight(productRequest.getWeight());
        product.setStock(productRequest.getStock());

        if (!image.isEmpty()) {
            storageService.updateImage(getFileName(product.getImage()), image);
        }

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Integer productId) {
        checkProductExistence(productId);
        Product product = productRepository.findProductById(productId);

        try {
            storageService.deleteImage(getFileName(product.getImage()));
        } catch (IOException e) {
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_REMOVE_FILE.getCode(),
                    ErrorResponse.FAILED_TO_REMOVE_FILE.getMessage()
            );
        }

        productRepository.delete(product);
        return ResponseConstant.DELETE_PRODUCT_SUCCESS;
    }

    @Override
    @Transactional("shopTransactionManager")
    public Product updateStock(Product product, int qty) {
        int updatedStock = product.getStock() - qty;
        product.setStock(updatedStock);

        return productRepository.save(product);
    }

    @Override
    public List<ReviewResponse> getProductReviews(Integer productId) {
        return reviewService.getProductReviews(productId);
    }

    private void checkProductExistence(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    private String getFileName(String imagePath) {
        return imagePath.substring(imagePath.lastIndexOf("/") + 1);
    }

}
