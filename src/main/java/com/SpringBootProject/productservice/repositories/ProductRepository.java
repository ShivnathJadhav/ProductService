package com.SpringBootProject.productservice.repositories;

import com.SpringBootProject.productservice.models.Product;
import com.SpringBootProject.productservice.projections.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM product p JOIN FETCH p.category WHERE p.name = :name AND p.category.name = :categoryName")
    Product findByNameAndCategory(@Param("name") String name, @Param("categoryName") String categoryName);

    @Query(value = "SELECT p.description, p.name, p.price FROM product p WHERE p.name = :name", nativeQuery = true)
    ProductInfo findProductInfoByName(@Param("name") String name);

    Product findByName(String name);
}
