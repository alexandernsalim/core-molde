package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import com.ta.coremolde.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("molde/api/v1/product")
public class ProductController extends GlobalController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Response<List<Product>> getAllProduct() {
        return toResponse(productService.getAllProduct());
    }

    @GetMapping("/{productId}/detail")
    public Response<Product> getProduct(@PathVariable Integer productId) {
        return toResponse(productService.getProduct(productId));
    }

    @PostMapping("/add")
    public Response<Product> addProduct(@ModelAttribute ProductRequest productRequest) {
        return toResponse(productService.addProduct(productRequest));
    }

    @PutMapping("/{productId}/update")
    public Response<Product> updateProduct(@PathVariable Integer productId, @ModelAttribute ProductRequest productRequest) {
        return toResponse(productService.updateProduct(productId, productRequest));
    }

    @DeleteMapping("/{productId}/delete")
    public Response<String> deleteProduct(@PathVariable Integer productId) {
        return toResponse(productService.deleteProduct(productId));
    }

}
