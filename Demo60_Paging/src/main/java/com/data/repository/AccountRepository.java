package com.data.repository;

import com.data.entity.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Configuration
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
