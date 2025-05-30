package com.data.controller;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import com.data.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private AccountService accountService;

    private PasswordEncoder passwordEncoder;

    public AccountController(AccountService accountService,
                             PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Account> opAccount = accountService.getById(id);
//        if (opAccount.isEmpty()) {
//            throw new EntityNotFoundException("Account not found, id = " + id);
//        }
        Account account = opAccount.get();
        return ResponseEntity.ok(account);
    }

    @PostMapping("account")
    public ResponseEntity<?> create(@RequestBody Account account) {
        // mã hoá pass 123 -> $2a255478dfsd6f6
        String passwordEncode = passwordEncoder.encode(account.getPassword());
        // set lại password
        account.setPassword(passwordEncode);
        accountService.save(account);

        return ResponseEntity.ok(account);
    }

}
