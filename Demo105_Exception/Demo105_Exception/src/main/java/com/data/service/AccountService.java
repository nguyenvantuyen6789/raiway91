package com.data.service;

import com.data.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> getById(int id);

    String save(Account account);

}
