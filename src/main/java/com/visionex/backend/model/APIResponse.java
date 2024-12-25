package com.visionex.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private String statusMessage;
    private String statusCode;
    private String transactionId;
    private String responseTime;
    private String origin;
    private String errorType;
    private Object result;
}
