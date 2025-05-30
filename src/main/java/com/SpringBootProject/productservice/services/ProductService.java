package com.SpringBootProject.productservice.services;

import com.SpringBootProject.productservice.dtos.CreateProductRequestDto;
import com.SpringBootProject.productservice.exceptions.BadRequestException;
import com.SpringBootProject.productservice.exceptions.ProductsNotFoundException;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductByName(String name) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws ProductsNotFoundException;
    public void deleteProductByNameAndCategory(String name, String category) throws ProductNotFoundException;
    public Product createProduct(CreateProductRequestDto createProductRequestDto) throws BadRequestException;
    public Product updateProduct(String name, String category, CreateProductRequestDto createProductRequestDto) throws ProductNotFoundException, BadRequestException;
    public Product patchProduct(String name, String category, CreateProductRequestDto createProductRequestDto) throws ProductNotFoundException, BadRequestException;
}