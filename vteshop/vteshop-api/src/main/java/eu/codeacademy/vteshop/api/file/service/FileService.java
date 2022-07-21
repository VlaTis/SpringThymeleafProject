package eu.codeacademy.vteshop.api.file.service;

import eu.codeacademy.vteshop.api.file.dto.FileResponse;
import eu.codeacademy.vteshop.jpa.file.entity.File;
import eu.codeacademy.vteshop.jpa.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@RequiredArgsConstructor
public class FileService {
    private final Path fileLocation = Paths.get("./files").toAbsolutePath().normalize();
    private final FileRepository fileRepository;

    @Transactional
    public FileResponse saveFile(MultipartFile file) {
        createDirectory();

        try {
            String[] splitFile = file.getOriginalFilename().split("\\.");

            File savedFileInDb = fileRepository.save(
                    File.builder()
                            .fileName(splitFile[0])
                            .fileExtension(splitFile[1])
                            .size(file.getSize())
                            .mediaType(file.getContentType())
                            .build());

            Path filePathWithFileName = fileLocation.resolve(savedFileInDb.getUniqFileName());
            Files.copy(file.getInputStream(), filePathWithFileName, StandardCopyOption.REPLACE_EXISTING);

            return FileResponse.builder()
                    .originalFileName(savedFileInDb.getUniqFileName())
                    .build();
        } catch (IOException e) {
            log.error("Cannot create file", e);
            e.printStackTrace();
        }

        return null;
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
        return MediaType.valueOf(URLConnection.guessContentTypeFromName(fileName));
    }
}


