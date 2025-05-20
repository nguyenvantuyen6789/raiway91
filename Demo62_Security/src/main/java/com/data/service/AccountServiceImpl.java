package com.data.service;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account is incorrect");
        }

        // Collections.emptyList(): Đăng nhập thành công
        return new User(username, account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole()));
    }
}
