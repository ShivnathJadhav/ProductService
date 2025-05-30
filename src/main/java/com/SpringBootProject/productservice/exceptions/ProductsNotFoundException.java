package com.SpringBootProject.productservice.exceptions;

public class ProductsNotFoundException extends Exception {
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
