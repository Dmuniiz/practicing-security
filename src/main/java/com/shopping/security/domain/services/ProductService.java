package com.shopping.security.domain.services;

import com.shopping.security.domain.product.Product;
import com.shopping.security.domain.product.ProductRequestDTO;
import com.shopping.security.domain.product.ProductResponseDTO;
import com.shopping.security.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public void postProduct(ProductRequestDTO body){
        var product = new Product(body);
        this.repository.save(product);
    }

    public List<ProductResponseDTO> listProduct(){
        return repository.findAll().stream().map(p-> new ProductResponseDTO(p)).toList();
    }

}
