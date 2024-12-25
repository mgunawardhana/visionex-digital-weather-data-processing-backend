package com.visionex.backend.exception;

import com.visionex.backend.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        APIResponse apiResponse = APIResponse.builder().statusCode("400").statusMessage("Bad Request").errorType(ex.getClass().getSimpleName()).result(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse> handleNullPointerException(NullPointerException ex, WebRequest request) {
        APIResponse apiResponse = APIResponse.builder().statusCode("500").statusMessage("Null Pointer Exception").errorType(ex.getClass().getSimpleName()).result(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<APIResponse> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex, WebRequest request) {
        APIResponse apiResponse = APIResponse.builder().statusCode("400").statusMessage("Index Out of Bounds").errorType(ex.getClass().getSimpleName()).result(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleAllExceptions(Exception ex, WebRequest request) {
        APIResponse apiResponse = APIResponse.builder().statusCode("500").statusMessage("Internal Server Error").errorType(ex.getClass().getSimpleName()).result(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<APIResponse> handleWebClientResponseException(WebClientResponseException ex, WebRequest request) {
        APIResponse apiResponse = APIResponse.builder().statusCode(String.valueOf(ex.getRawStatusCode())).statusMessage(ex.getStatusText()).errorType(ex.getClass().getSimpleName()).result(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, ex.getStatusCode());
    }
}
