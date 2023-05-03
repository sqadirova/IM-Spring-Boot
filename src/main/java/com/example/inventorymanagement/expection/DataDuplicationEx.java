package com.example.inventorymanagement.expection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
//@ResponseStatus(value = HttpStatus.CONFLICT)
public class DataDuplicationEx  extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataDuplicationEx(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse(message, developerMessage, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }
}
