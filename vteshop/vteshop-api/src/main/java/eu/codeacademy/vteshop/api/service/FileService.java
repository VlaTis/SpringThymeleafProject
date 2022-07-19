package eu.codeacademy.vteshop.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@Slf4j
public class FileService {
    private final Path fileLocation = Paths.get("./files").toAbsolutePath().normalize();

    public void saveFile(MultipartFile file) {
        createDirectory();
        try {
            Path filePathWithFileName = fileLocation.resolve(getUniqFileName(file));
            Files.copy(file.getInputStream(), filePathWithFileName , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Cannot create file", e);
            e.printStackTrace();
        }
    }

    private String getUniqFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int nanoDate = LocalDateTime.now().getNano();

        return String.format("%s_%s", nanoDate, fileName);
    }

    private void createDirectory() {
        try {
            if (!Files.exists(fileLocation)) {
                Files.createDirectory(fileLocation);
            }
        } catch (IOException e) {
            log.error("Cannot create directory", e);
            e.printStackTrace();
        }
    }

    public Resource getFile(String fileName) {
        try {
            InputStream inputStream = Files.newInputStream(fileLocation.resolve(fileName)); //fileLocation.resolve(fileName) - full path
            return new InputStreamResource(inputStream);
        } catch (IOException e) {
            log.error("Cannot get/create file by name", e);
            e.printStackTrace();
        }

        return null;
    }

    public MediaType getFileMediaTypeByFileName(String fileName) {
        return MediaType.valueOf(URLConnection.guessContentTypeFromName(fileName)) ;
    }
}


