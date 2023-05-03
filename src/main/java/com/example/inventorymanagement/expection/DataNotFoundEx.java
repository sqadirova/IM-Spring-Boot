package com.example.inventorymanagement.expection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundEx extends RuntimeException {
//    @Serial
//    private static final long serialVersionUID = 1L;
//    private String message;

    //    public DataNotFoundEx(String message) {
//        this.message = message;
//    }

    private ErrorResponse errorResponse;

    public DataNotFoundEx(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse(message, developerMessage, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

}
