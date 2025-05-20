package com.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // k mã hoá
//        return NoOpPasswordEncoder.getInstance();

        // mã hoá 2a$sdfds324
        return new BCryptPasswordEncoder();
    }

    // config role
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .requestMatchers("/products/**").hasAnyRole("ADMIN"); // t1
        http.authorizeRequests()
                .requestMatchers("/accounts/**").hasAnyRole("CUSTOMER"); // t2
        http.authorizeRequests()
                .requestMatchers("/categories/**").permitAll(); // cho phep tat ca

        return http.httpBasic().and().build();
    }

}
