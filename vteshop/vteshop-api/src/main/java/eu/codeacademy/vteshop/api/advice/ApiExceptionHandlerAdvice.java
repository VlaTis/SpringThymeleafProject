package eu.codeacademy.vteshop.api.advice;


import eu.codeacademy.vteshop.api.dto.ApiExceptionResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(FileSizeLimitExceededException.class) // throw exception file limit exceed
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE) // changing response status on this exception
    public ApiExceptionResponse handleFileToLargeException(FileSizeLimitExceededException ex) {
        return ApiExceptionResponse.builder()
                .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(SQLException.class) // throw exception file limit exceed
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // changing response status on this exception
    public ApiExceptionResponse handleFileToLargeException(SQLException ex) {
        return ApiExceptionResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Can not be deleted/updated---")
                .build();
    }
}
