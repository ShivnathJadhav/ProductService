package com.SpringBootProject.productservice.services;

import com.SpringBootProject.productservice.dtos.FakeStoreProductDto;
import com.SpringBootProject.productservice.exceptions.BadRequestException;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.exceptions.ProductsNotFoundException;
import com.SpringBootProject.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service ("fakeStoreImpl")
public class FakeStoreProductService{

    public Product getProductById(int id) throws ProductNotFoundException {
        /*
        https://fakestoreapi.com/products/{id}
        */
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(url, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }
        return fakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    public List<Product> getAllProducts() throws ProductsNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);
        if(fakeStoreProductDtos == null || fakeStoreProductDtos.length == 0) {
            throw new ProductsNotFoundException("No products found.");
        }
        return List.of(fakeStoreProductDtos).stream()
                .map(this::fakeStoreProductDtoToProduct)
                .toList();
    }


    public Product addProduct(Product product) throws BadRequestException{
        // This method is not applicable for FakeStoreProductService as it fetches products from an external API.
        // You can implement this method if you want to add products to a local database or another service.
        if(product == null || product.getName() == null || product.getPrice() <= 0) {
            throw new BadRequestException("Invalid product data.");
        }
        return product; // Returning the product as is, since we cannot add it to the fake store API.
    }


    public void deleteProductById(int id) throws ProductNotFoundException {
        // This method is not applicable for FakeStoreProductService as it fetches products from an external API.
        // You can implement this method if you want to delete products from a local database or another service.
        if(id<=0){
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }
    }


    public void createProduct(String name, double price, String description, String Category) {

    }


    private Product fakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
