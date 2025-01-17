package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.shop.service.StorageService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String UPLOAD_PRODUCT_PATH = System.getProperty("user.dir") + "/src/main/upload/images/";
    private static final String UPLOAD_PAYMENT_PATH = System.getProperty("user.dir") + "/src/main/upload/payments/";

    @Override
    public ResponseEntity loadImage(String image) {
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(getResource(UPLOAD_PRODUCT_PATH, image).getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public void saveImage(Integer shopId, MultipartFile image) {
        String filename = StringUtils.cleanPath(image.getOriginalFilename());

        try {
            isEmpty(image);
            isFaultFileName(filename);

            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, Paths.get(UPLOAD_PRODUCT_PATH + "shop-" + shopId + "-" + filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_STORE_FILE.getCode(),
                    ErrorResponse.FAILED_TO_STORE_FILE.getMessage()
            );
        }
    }

    @Override
    public void updateImage(String filename, MultipartFile image) {
        try {
            InputStream inputStream = image.getInputStream();

            Files.copy(inputStream, Paths.get(UPLOAD_PRODUCT_PATH + filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_STORE_FILE.getCode(),
                    ErrorResponse.FAILED_TO_STORE_FILE.getMessage()
            );
        }
    }

    @Override
    public void deleteImage(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_PRODUCT_PATH + filename);
        Files.delete(file);
    }

    @Override
    public ResponseEntity loadPaymentImage(String image) {
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(getResource(UPLOAD_PAYMENT_PATH, image).getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public void savePaymentImage(Integer shopId, String filename, MultipartFile image) {
        try {
            isEmpty(image);
            isFaultFileName(filename);

            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, Paths.get(UPLOAD_PAYMENT_PATH + "shop-" + shopId + "-payment-" + filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_STORE_FILE.getCode(),
                    ErrorResponse.FAILED_TO_STORE_FILE.getMessage()
            );
        }
    }

    @Override
    public void updatePaymentImage(String filename, MultipartFile image) {
        try {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, Paths.get(UPLOAD_PAYMENT_PATH + filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_STORE_FILE.getCode(),
                    ErrorResponse.FAILED_TO_STORE_FILE.getMessage()
            );
        }
    }

    @Override
    public void deletePaymentImage(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_PAYMENT_PATH + filename);
        Files.delete(file);
    }

    private void isEmpty(MultipartFile image) {
        if (image.isEmpty()) {
            throw new MoldeException(
                    ErrorResponse.FILE_EMPTY.getCode(),
                    ErrorResponse.FILE_EMPTY.getMessage()
            );
        }
    }

    private void isFaultFileName(String filename) {
        if (filename.contains("..")) {
            throw new MoldeException(
                    ErrorResponse.FILE_NAME_FAULT.getCode(),
                    ErrorResponse.FILE_NAME_FAULT.getMessage()
            );
        }
    }

    private Resource getResource(String path, String filename) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String location = "file:" + path + filename;

        return resourceLoader.getResource(location);
    }

}
