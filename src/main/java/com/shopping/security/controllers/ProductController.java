package com.shopping.security.controllers;


import com.shopping.security.domain.product.Product;
import com.shopping.security.domain.product.ProductRequestDTO;
import com.shopping.security.domain.product.ProductResponseDTO;
import com.shopping.security.domain.services.ProductService;
import com.shopping.security.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        try{
            service.postProduct(body);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct(){
        return ResponseEntity.ok(service.listProduct());
    }

}
