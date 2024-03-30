package com.lpnu.shaggybeavers.util;

import com.lpnu.shaggybeavers.dto.FileDTO;
import com.lpnu.shaggybeavers.exception.FileException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    public static File toFile(MultipartFile multipartFile) throws IOException {
        String extension = getExtension(multipartFile);
        File file = File.createTempFile("temp", extension);
        multipartFile.transferTo(file);
        return file;
    }

    public static Resource zipFiles(List<FileDTO> dtos) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (int i = 0; i < dtos.size(); i++) {
                Resource resource = dtos.get(i).getFile();
                zos.putNextEntry(new ZipEntry("file" + i + dtos.get(i).getExtension()));
                zos.write(resource.getContentAsByteArray());
                zos.closeEntry();
            }
        } catch (IOException e) {
            throw new FileException("Zipping files failed");
        }

        return new ByteArrayResource(baos.toByteArray());
    }

    public static String getExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.'));
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
