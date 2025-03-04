package com.github.sangarru11.diccionariosantiago.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    private String exceptionDetail;
    private Object fieldValue;

    public RecordNotFoundException(String exceptionDetail, Object fieldValue) {
        super(exceptionDetail + " - " + fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
