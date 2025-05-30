package com.SpringBootProject.productservice.advices;

import com.SpringBootProject.productservice.exceptions.BadRequestException;
import com.SpringBootProject.productservice.exceptions.ProductsNotFoundException;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CotrollerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> getProductNotFoundException(ProductNotFoundException p){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(p.getMessage());
    }
    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<String> getNoProductsFoundException(ProductsNotFoundException p){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(p.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> getBadRequestException(BadRequestException p){
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(p.getMessage());
    }
}