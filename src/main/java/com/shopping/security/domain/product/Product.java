package com.shopping.security.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "product")
@Entity(name = "product")
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Integer price;


    Product() {}

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
    }


    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
