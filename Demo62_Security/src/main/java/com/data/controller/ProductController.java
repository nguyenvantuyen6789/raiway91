package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    // customer vào được
    @GetMapping("/products")
    public String getProduct() {
        return "Car";
    }

    // admin vào được
    @GetMapping("/products/{id}")
    public String getById(@PathVariable Integer id) {
        return "Vios 2018, Blue";
    }
    // thêm dependency spring-data-jpa, mysql,
    // roi lam api show all account: id, username, password, fullName, role
}
