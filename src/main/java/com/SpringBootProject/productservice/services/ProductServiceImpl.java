package com.SpringBootProject.productservice.services;

import com.SpringBootProject.productservice.dtos.CreateProductRequestDto;
import com.SpringBootProject.productservice.exceptions.BadRequestException;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.exceptions.ProductsNotFoundException;
import com.SpringBootProject.productservice.models.Category;
import com.SpringBootProject.productservice.models.Product;
import com.SpringBootProject.productservice.projections.ProductInfo;
import com.SpringBootProject.productservice.repositories.CategoryRepository;
import com.SpringBootProject.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DBImpl")
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductByName(String name) throws ProductNotFoundException {

        // demo to validate the projection
        ProductInfo productInfo = productRepository.findProductInfoByName(name);
        System.out.println("Product Info: " + productInfo.getDescription() +
                ", " + productInfo.getName() + ", " + productInfo.getPrice());

        if(name == null || name.isEmpty()) {
            throw new ProductNotFoundException("Product with Name " + name + " not found.");
        }
        Product product = productRepository.findByName(name);
        if(product == null) {
            throw new ProductNotFoundException("Product with Name " + name + " not found.");
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() throws ProductsNotFoundException {
        List<Product> products = productRepository.findAll();
        if(products == null || products.isEmpty()) {
            throw new ProductsNotFoundException("No products found.");
        }
        return products;
    }


    @Override
    public void deleteProductByNameAndCategory(String name, String category) throws ProductNotFoundException {
        if(name == null || name.isEmpty() || category == null || category.isEmpty()) {
            throw new ProductNotFoundException("Product with Name " + name + " and Category " + category + " not found.");
        }
        Product product = productRepository.findByNameAndCategory(name, category);
        if(product == null) {
            throw new ProductNotFoundException("Product with Name " + name + " and Category " + category + " not found.");
        }
        productRepository.delete(product);

    }

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) throws BadRequestException{
        if (createProductRequestDto == null || createProductRequestDto.getName() == null || createProductRequestDto.getPrice() <= 0) {
            throw new BadRequestException("Invalid product data.");
        }
        Product product = productRepository.findByNameAndCategory(createProductRequestDto.getName(), createProductRequestDto.getCategory());
        if(product != null) {
            return product; // If the product already exists, return it
        }
        Product newProduct = new Product();
        newProduct.setName(createProductRequestDto.getName());
        newProduct.setPrice(createProductRequestDto.getPrice());
        newProduct.setDescription(createProductRequestDto.getDescription());

        String categoryName = createProductRequestDto.getCategory();
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
        }
        newProduct.setCategory(category);

        productRepository.save(newProduct);

        return newProduct;
    }

    @Override
    public Product updateProduct(String name, String category, CreateProductRequestDto createProductRequestDto) throws ProductNotFoundException, BadRequestException {
        if (createProductRequestDto == null || createProductRequestDto.getName() == null || createProductRequestDto.getCategory() == null) {
            throw new BadRequestException("Invalid product data.");
        }
        Product existingProduct = productRepository.findByNameAndCategory(name, category);
        if(existingProduct == null) {
            throw new ProductNotFoundException("Product with Name " + createProductRequestDto.getName() + " and Category " + createProductRequestDto.getCategory() + " not found.");
        }
        existingProduct.setName(createProductRequestDto.getName());
        existingProduct.setPrice(createProductRequestDto.getPrice());
        existingProduct.setDescription(createProductRequestDto.getDescription());

        String categoryName = createProductRequestDto.getCategory();
        Category cat = categoryRepository.findByName(categoryName);
        if (cat == null) {
            cat = new Category();
            cat.setName(categoryName);
            categoryRepository.save(cat);
        }

        existingProduct.setCategory(cat);
        productRepository.save(existingProduct);
        return existingProduct;
    }
    public Product patchProduct(String name, String category, CreateProductRequestDto createProductRequestDto) throws ProductNotFoundException, BadRequestException {
        if (createProductRequestDto == null ) {
            throw new BadRequestException("Invalid product data.");
        }
        Product existingProduct = productRepository.findByNameAndCategory(name, category);
        if(existingProduct == null) {
            throw new ProductNotFoundException("Product with Name " + createProductRequestDto.getName() + " and Category " + createProductRequestDto.getCategory() + " not found.");
        }
        if(createProductRequestDto.getPrice() > 0) {
            existingProduct.setPrice(createProductRequestDto.getPrice());
        }
        if(createProductRequestDto.getDescription() != null && !createProductRequestDto.getDescription().isEmpty()) {
            existingProduct.setDescription(createProductRequestDto.getDescription());
        }
        if(createProductRequestDto.getName() != null && !createProductRequestDto.getName().isEmpty() && !createProductRequestDto.getName().equals(existingProduct.getName())) {
            existingProduct.setName(createProductRequestDto.getName());
        }
        if(createProductRequestDto.getCategory() != null && !createProductRequestDto.getCategory().isEmpty() && !createProductRequestDto.getCategory().equals(existingProduct.getCategory().getName())) {
            existingProduct.setCategory(categoryRepository.findByName(createProductRequestDto.getCategory()));
        }
        productRepository.save(existingProduct);
        return existingProduct;
    }
}
