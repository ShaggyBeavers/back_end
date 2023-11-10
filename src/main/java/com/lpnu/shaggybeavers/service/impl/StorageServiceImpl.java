package com.lpnu.shaggybeavers.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lpnu.shaggybeavers.exception.FileException;
import com.lpnu.shaggybeavers.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public String uploadFile(MultipartFile multipartFile) {
        File file = MultipartFileToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new FileException("The file was not deleted successfully");
        }
        return String.valueOf(s3Client.getUrl(bucketName, fileName));
    }

    private File MultipartFileToFile(MultipartFile multipartFile) {
        try {
            File file = File.createTempFile(multipartFile.getName() + "tmp", null);
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            throw new FileException("The file was not converted successfully: " + e.getMessage());
        }
    }

}
