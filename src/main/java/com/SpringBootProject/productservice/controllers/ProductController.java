package com.SpringBootProject.productservice.controllers;

import com.SpringBootProject.productservice.dtos.CreateProductRequestDto;
import com.SpringBootProject.productservice.exceptions.BadRequestException;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.exceptions.ProductsNotFoundException;
import com.SpringBootProject.productservice.models.Product;
import com.SpringBootProject.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    @Qualifier("DBImpl") // Change to "fakeStoreImpl" if you want to use the FakeStoreProductService
    private ProductService productService;

    // GET / Products/{id}
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductById(@PathVariable String name) throws ProductNotFoundException {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws ProductsNotFoundException {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) throws BadRequestException {
        Product p = productService.createProduct(createProductRequestDto);
        return new ResponseEntity<>(p,CREATED);
    }

    @DeleteMapping("/{name}/{category}")
    public ResponseEntity<String> deleteProductByNameAndCategory(@PathVariable String name, @PathVariable String category) throws ProductNotFoundException {
        productService.deleteProductByNameAndCategory(name, category);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).body("Product deleted successfully");
    }

    @PutMapping("/{name}/category")
    public ResponseEntity<Product> updateProduct(@PathVariable String name,@PathVariable String category, @RequestBody CreateProductRequestDto product) throws ProductNotFoundException, BadRequestException {
        Product updatedProduct = productService.updateProduct(name, category, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @PatchMapping("/{name}/{category}")
    public ResponseEntity<Product> patchProduct(@PathVariable String name, @PathVariable String category, @RequestBody CreateProductRequestDto product) throws ProductNotFoundException, BadRequestException {
        Product updatedProduct = productService.patchProduct(name, category, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

}
