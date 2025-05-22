package com.data.controller;

import com.data.dto.AccountDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AccountController {

    @PostMapping("/accounts")
    public String getAccounts(@Valid @RequestBody AccountDTO accountDTO) {
        System.out.println("accountDTO");
        System.out.println(accountDTO);
        return "Account created: " + accountDTO.getUsername();
    }
}
