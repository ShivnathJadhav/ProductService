package com.SpringBootProject.productservice.controllers;

import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.models.Product;
import com.SpringBootProject.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService fakeStoreProductService;

    // GET / Products/{id}
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ("id") int id) throws ProductNotFoundException {
        Product product = fakeStoreProductService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

}
