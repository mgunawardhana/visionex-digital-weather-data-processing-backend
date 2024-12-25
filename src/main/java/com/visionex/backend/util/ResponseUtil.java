package com.visionex.backend.util;

import com.visionex.backend.model.APIResponse;
import com.visionex.backend.model.enums.StatusCode;
import com.visionex.backend.model.enums.StatusMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class ResponseUtil {

    private final HttpServletRequest servletRequest;

    public ResponseUtil(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    /**
     * Wraps a successful response in an APIResponse object.
     *
     * @param value      the value to include in the response
     * @param httpStatus the HTTP status to set for the response
     * @return a ResponseEntity containing the APIResponse object with success status
     */
    public ResponseEntity<APIResponse> wrapSuccess(Object value, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder().statusCode(StatusCode.SUCCESS.valueOf())
                .origin(servletRequest.getRequestURI()).statusMessage(StatusMessage.SUCCESS.valueOf())
                .responseTime(DateTimeUtils.format(new Date())).result(value).build();
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    /**
     * Wraps an error response in an APIResponse object.
     *
     * @param value      the value to include in the response
     * @param errorType  the type of error to include in the response
     * @param httpStatus the HTTP status to set for the response
     * @return a ResponseEntity containing the APIResponse object with error status
     */
    public ResponseEntity<APIResponse> wrapError(Object value, String errorType, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder().statusCode(StatusCode.FAILURE.valueOf())
                .statusMessage(StatusMessage.FAILURE.valueOf()).errorType(errorType).origin(servletRequest.getRequestURI())
                .responseTime(DateTimeUtils.format(new Date())).result(Collections.singletonMap(Constant.ERROR, value)).build();
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
