package com.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController: tiếp nhận HTTP Request từ trình duyệt,
// Postman: Trả về Json, String, ...
// @RestController

// Controller tiếp nhận HTTP Request từ trình duyệt,
// Postman: Trả về view (Html)
@Controller
public class HomeController {

    @GetMapping("")
    public String getAll() {
        return "index";
    }

}
