package com.application.rest.services.implement;

import com.application.rest.entities.Product;
import com.application.rest.persistence.implement.ProductDAOImplement;
import com.application.rest.repositories.MakerRepository;
import com.application.rest.repositories.ProductRepository;
import com.application.rest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements IProductService {

    @Autowired
    private ProductDAOImplement productDAOImplement;
    @Override
    public List<Product> findAllProduct() {
        return (List<Product>) productDAOImplement.findAllProduct();
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productDAOImplement.findByPriceRange(minPrice, maxPrice);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productDAOImplement.findById(id);
    }

    @Override
    public void save(Product product) {
        productDAOImplement.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDAOImplement.deleteById(id);
    }
}
