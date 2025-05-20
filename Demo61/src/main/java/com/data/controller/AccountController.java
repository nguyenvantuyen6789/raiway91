package com.data.controller;

import com.data.dto.AccountDTO;
import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

     @Autowired
     private AccountRepository accountRepo;

     @Autowired
     private ModelMapper modelMapper;

    @GetMapping("account/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Account> opAccount = accountRepo.findById(id);
        if (opAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Account account = opAccount.get();
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("account")
    public ResponseEntity<?> getAll() {
        List<Account> accounts = accountRepo.findAll();
        List<AccountDTO> accountDTOs = modelMapper.map(accounts,
                new TypeToken<List<AccountDTO>>() {}.getType());

        return ResponseEntity.ok(accountDTOs);
    }
}
