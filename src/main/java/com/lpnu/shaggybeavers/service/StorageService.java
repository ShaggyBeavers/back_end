package com.lpnu.shaggybeavers.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface StorageService {

    URL uploadFile(MultipartFile file) throws IOException;

    URL downloadFile(String fileName);

}
