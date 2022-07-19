package eu.codeacademy.vteshop.api.advice;


import eu.codeacademy.vteshop.api.dto.ApiExceptionResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ApiExceptionResponse handleFileToLargeException(FileSizeLimitExceededException ex) {
        return ApiExceptionResponse.builder()
                .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message(ex.getMessage())
                .build();
    }
}
