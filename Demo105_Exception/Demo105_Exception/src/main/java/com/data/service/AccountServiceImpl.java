package com.data.service;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;

    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Optional<Account> getById(int id) {
        return accountRepo.findById(id);
    }

    @Override
    public String save(Account account) {
        accountRepo.save(account);
        return "Save Success";
    }

}
