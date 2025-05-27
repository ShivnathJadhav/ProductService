package com.SpringBootProject.productservice.services;

import com.SpringBootProject.productservice.dtos.FakeStoreProductDto;
import com.SpringBootProject.productservice.exceptions.ProductNotFoundException;
import com.SpringBootProject.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    @Override
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
    private Product fakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
