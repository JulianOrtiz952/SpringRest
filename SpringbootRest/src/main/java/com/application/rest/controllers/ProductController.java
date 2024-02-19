package com.application.rest.controllers;

import com.application.rest.controllers.dto.MakerDTO;
import com.application.rest.controllers.dto.ProductDTO;
import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.services.IMakerService;
import com.application.rest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping("/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> productOptional= productService.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();

            ProductDTO productDTO = ProductDTO.builder().id(product.getId()).name(product.getName()).Price(product.getPrice()).build();
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll(){
        List<ProductDTO> productDTOList = productService.findAllProduct().stream().map(product -> ProductDTO.builder().name(product.getName()).id(product.getId()).Price(product.getPrice()).build()).toList();
        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping("/save")
    ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        if(productDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        productService.save(Product.builder().name(productDTO.getName()).price(productDTO.getPrice()).maker(productDTO.getMaker()).build());
        return ResponseEntity.created(new URI("/api/product/save")).build();
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Optional<Product> productOptional = productService.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setMaker(productDTO.getMaker());
            productService.save(product);
            return ResponseEntity.ok("product has been updated");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        if(id!=null){
            productService.deleteById(id);
            return ResponseEntity.ok("product has been deleted");
        }
        return ResponseEntity.badRequest().build();
    }

}
