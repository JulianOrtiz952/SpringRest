package com.application.rest.persistence;

import com.application.rest.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> findAllProduct();
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Product> findById(Long id);
    void save(Product product);
    void deleteById(Long id);
}
