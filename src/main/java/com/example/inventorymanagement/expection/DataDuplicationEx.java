package com.example.inventorymanagement.expection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataDuplicationEx  extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataDuplicationEx(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse(message, developerMessage, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }
}
