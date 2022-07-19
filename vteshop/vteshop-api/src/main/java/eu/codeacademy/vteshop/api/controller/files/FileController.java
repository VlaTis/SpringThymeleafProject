package eu.codeacademy.vteshop.api.controller.files;

import eu.codeacademy.vteshop.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/file/upload")
    public void saveFile(@RequestParam MultipartFile file) {
        fileService.saveFile(file);

    }

    @GetMapping("/api/file/download")
    public ResponseEntity<Resource> getFileByName(@RequestParam String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // add info to header
        return ResponseEntity.ok()   //Response entity to expand response (add some features headers, status etc.)
                .contentType(fileService.getFileMediaTypeByFileName(fileName))      //Content type for type of files
                .body(fileService.getFile(fileName));

    }
}
