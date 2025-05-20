package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data // @Data cua lombok gom: getter, setter, toString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
