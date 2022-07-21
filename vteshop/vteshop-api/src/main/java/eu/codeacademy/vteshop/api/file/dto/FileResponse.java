package eu.codeacademy.vteshop.api.file.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileResponse {

    private String originalFileName;
}