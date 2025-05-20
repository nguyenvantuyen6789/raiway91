package com.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping: Tao duong path chung cho ca controller
@RequestMapping("orders")
public class OrdersController {

    // path /orders + method GET: lay tat ca orders trong db
    // path /orders/id + method GET: lay 1 orders trong db by id
    // path /orders + method POST: tao moi orders
    // path /orders + method PUT: cap nhat orders
    // path /orders/id + method DELETE: Xoa 1 orders trong db by id

    @GetMapping("data/{id}")
    public ResponseEntity<?> getOrders(@PathVariable int id) {
        System.out.println("Id: " + id);
        return new ResponseEntity<>("Get thanh cong", HttpStatus.OK);
    }

    @GetMapping("data2/{name}")
    public ResponseEntity<?> getOrders2(@PathVariable String name) {
        System.out.println("name: " + name);
        return new ResponseEntity<>("Get thanh cong2", HttpStatus.OK);
    }

    // tham so name: @PathVariable(name="age2") String name,
    // map variable tren path
    @GetMapping("data3/{name}/{age}")
    public ResponseEntity<?> getOrders3(@PathVariable String name,
                                        @PathVariable int age) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return new ResponseEntity<>("Get thanh cong2", HttpStatus.OK);
    }

}
