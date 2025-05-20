package com.data.controller;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

     @Autowired
     private AccountRepository accountRepo;

    @GetMapping("accounts")
    public ResponseEntity<?> getAll(Pageable pageable) {
        System.out.println(pageable);
        // page: ngoài dữ liệu list, sẽ có thông tin của phân trang
        // total page, current page, size, total element
        Page<Account> accounts = accountRepo.findAll(pageable);
        return ResponseEntity.ok(accounts);
    }
}
