package com.data.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Product {

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 15000, message = "Price must be less than or equal to 15000")
    private double price;
    private String description;

    public Product() {
    }

    public Product(String productName, double price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
}
