package com.SpringBootProject.productservice.repositories;

import com.SpringBootProject.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByNameAndCategory(String name, String category);
    Product findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.name = ?1 AND p.category = ?2")
    Product findByNameAndCategoryQuery(String name, String category);
}
