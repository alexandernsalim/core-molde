package com.ta.coremolde.shop.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    ResponseEntity loadImage(String image);
    void saveImage(Integer shopId, MultipartFile image);
    void updateImage(String filename, MultipartFile image);
    void deleteImage(String filename) throws IOException;

    ResponseEntity loadPaymentImage(String image);
    void savePaymentImage(Integer shopId, String filename, MultipartFile image);
    void updatePaymentImage(String filename, MultipartFile image);
    void deletePaymentImage(String filename) throws IOException;

}
