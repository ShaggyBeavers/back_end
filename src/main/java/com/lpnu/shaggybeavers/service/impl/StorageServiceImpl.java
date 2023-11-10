package com.lpnu.shaggybeavers.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lpnu.shaggybeavers.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Value("")
    private String bucketName;

    private final AmazonS3 s3Client;

    public URL uploadFile(MultipartFile multipartFile) throws IOException {
        File file = MultipartFileToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new IOException("The file was not deleted.");
        }
        return s3Client.getUrl(bucketName, fileName);
    }

    public URL downloadFile(String fileName) {
        return s3Client.getUrl(bucketName, fileName);
    }

    private File MultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File("src/main/resources/targetFile.tmp");
        multipartFile.transferTo(file);
        return file;
    }

}
