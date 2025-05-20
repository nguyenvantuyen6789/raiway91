package com.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    // tên method "getInfo" k quan trọng, dùng path /getAccount là chính
    @GetMapping("getAccount")
    public String getInfo() {
        return "Username: t1";
    }
    // dùng post ở postman
    @PostMapping("getAccount")
    public String createData() {
        return "Create account successfully!";
    }
    @PutMapping("getAccount")
    public String updateData() {
        return "Update account successfully!";
    }
    @DeleteMapping("getAccount")
    public String deleteData() {
        return "Delete account successfully!";
    }

    // đổi cổng mặc định 8080, thì vào application.properties
    // set server.port

    // Spring ho tro kieu tra ve da nang cho tat ca kieu du lieu
    // ResponseEntity<?>: String, int, boolean, Product, List...
    // Tra ve trang thai cua response: OK: Thanh cong: Ma 200
    // 201: CREATED: Tao du lieu thanh cong
    // 500: Loi code do lap trinh vien
    // 404: NOT FOUND: Sai duong path o postman hoac trinh duyet
    // 400: Bad request, gui lieu len sai
    // 405: Duong dan dung nhung method sai (method get, post, put, ..)

    @GetMapping("getAcc")
    public ResponseEntity<?> getAcc() {
        // tra ve so
//        return new ResponseEntity<>(15, HttpStatus.OK);
        // tra ve chuoi
//        return new ResponseEntity<>("Password: 123", HttpStatus.OK);
        // tra ve boolean
        return new ResponseEntity<>(false, HttpStatus.CREATED);
    }
}
