package eu.codeacademy.vteshop.api.file.controller;

import eu.codeacademy.vteshop.api.file.dto.FileResponse;
import eu.codeacademy.vteshop.api.file.service.FileService;
import eu.codeacademy.vteshop.common.OpenApi;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Api(tags = "File Controller")
@OpenApi
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/file/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FileResponse saveFile(@RequestParam MultipartFile file) {
       return fileService.saveFile(file);

    }

    @GetMapping("/api/file/download")
    public ResponseEntity<Resource> getFileByName(@RequestParam String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // add info to header
        return ResponseEntity.ok()
                .headers(headers)//Response entity to expand response (add some features headers, status etc.)
                .contentType(fileService.getFileMediaTypeByFileName(fileName))      //Content type for type of files
                .body(fileService.getFile(fileName));

    }
}
