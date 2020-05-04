package com.ta.coremolde.master.controller;

import com.ta.coremolde.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/molde/api/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/product/{image}")
    public ResponseEntity<InputStreamResource> getProductImage(@PathVariable String image) {
        return storageService.loadImage(image);
    }

    @GetMapping("payment/{image}")
    public ResponseEntity<InputStreamResource> getPaymentImage(@PathVariable String image) {
        return storageService.loadPaymentImage(image);
    }

}
