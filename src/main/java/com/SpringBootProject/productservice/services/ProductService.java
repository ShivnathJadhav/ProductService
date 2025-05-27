package com.SpringBootProject.productservice.services;

import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    public Product getProductById(int id) throws ProductNotFoundException;
}