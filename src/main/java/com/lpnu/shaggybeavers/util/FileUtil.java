package com.lpnu.shaggybeavers.util;

import com.lpnu.shaggybeavers.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File toFile(MultipartFile multipartFile) throws IOException {
        String extension = getExtension(multipartFile);
        File file = File.createTempFile("temp", extension);
        multipartFile.transferTo(file);
        return file;
    }

    private static String getExtension(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new FileException("MultipartFile mustn't be null or empty");
        }

        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new FileException("OriginalFilename is null or empty");
        }

        if (!originalFilename.contains(".")) {
            throw new FileException("No extension of found for file: " + originalFilename);
        }

        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

}
