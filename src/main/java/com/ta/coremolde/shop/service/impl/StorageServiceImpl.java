package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.shop.service.StorageService;
import org.aspectj.util.FileUtil;
import org.springframework.core.io.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String UPLOAD_PATH = System.getProperty("user.dir") + "/src/main/upload/images/";

    @Override
    public ResponseEntity loadImage(String image) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String location = "file:" + UPLOAD_PATH + image;
        Resource resource = resourceLoader.getResource(location);

        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(resource.getInputStream()));
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
            if (image.isEmpty()) {
                throw new MoldeException(
                        ErrorResponse.FILE_EMPTY.getCode(),
                        ErrorResponse.FILE_EMPTY.getMessage()
                );
            }

            if (filename.contains("..")) {
                throw new MoldeException(
                        ErrorResponse.FILE_NAME_FAULT.getCode(),
                        ErrorResponse.FILE_NAME_FAULT.getMessage()
                );
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_PATH + "shop-" + shopId + "-" + filename), StandardCopyOption.REPLACE_EXISTING);
            }

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

            Files.copy(inputStream, Paths.get(UPLOAD_PATH + filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MoldeException(
                ErrorResponse.FAILED_TO_STORE_FILE.getCode(),
                ErrorResponse.FAILED_TO_STORE_FILE.getMessage()
            );
        }
    }

    @Override
    public void deleteImage(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_PATH + filename);
        Files.delete(file);
    }

}
