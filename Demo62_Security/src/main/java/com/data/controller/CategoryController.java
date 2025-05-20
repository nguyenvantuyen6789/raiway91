package com.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    // customer vào được
    @GetMapping("/categories")
    public String get() {
        return "Quan ao";
    }

}
