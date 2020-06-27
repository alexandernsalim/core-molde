package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.ExportService;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.ProductRequest;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import com.ta.coremolde.shop.service.DiscussionService;
import com.ta.coremolde.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("molde/api/v1/product")
public class ProductController extends GlobalController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/all")
    public Response<List<Product>> getAllProduct() {
        return toResponse(productService.getAllProduct());
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportProduct() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=products.xlsx");
        ByteArrayInputStream file = exportService.exportProduct();

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(file));
    }

    @GetMapping("/{productId}/detail")
    public Response<Product> getProduct(@PathVariable Integer productId) {
        return toResponse(productService.getProduct(productId));
    }

    @GetMapping("/{productId}/discussions")
    public Response<List<DiscussionServiceResponse>> getProductDiscussions(@PathVariable Integer productId) {
        return toResponse(discussionService.getProductDiscussions(productId));
    }

    @GetMapping("/{productId}/reviews")
    public Response<List<ReviewResponse>> getProductReviews(@PathVariable Integer productId) {
        return toResponse(productService.getProductReviews(productId));
    }

    @PostMapping("/add")
    public Response<Product> addProduct(@ModelAttribute ProductRequest productRequest, @RequestParam MultipartFile image, HttpServletRequest httpServletRequest) {
        Shop shop = shopService.getShopByAccountEmail(httpServletRequest.getUserPrincipal().getName());

        return toResponse(productService.addProduct(shop.getId(), productRequest, image));
    }

    @PutMapping("/{productId}/update")
    public Response<Product> updateProduct(@PathVariable Integer productId, @ModelAttribute ProductRequest productRequest, @RequestParam MultipartFile image) {
        return toResponse(productService.updateProduct(productId, productRequest, image));
    }

    @DeleteMapping("/{productId}/delete")
    public Response<String> deleteProduct(@PathVariable Integer productId) {
        return toResponse(productService.deleteProduct(productId));
    }

}
