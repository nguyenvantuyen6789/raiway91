package com.data.controller;

import com.data.entity.Product;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ProductController {

     @PostMapping("products")
     public String getAllProducts(@Valid @RequestBody Product product) {
         System.out.println(product);

         return "Car";
     }

}
