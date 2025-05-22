package com.data.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Size(min = 6, max = 10, message = "Password: 6 - 10 characters")
    private String password;
    private String fullName;

    // a@**.**
    @Email(message = "Email is not valid")
    private String email;

    @Min(value = 0, message = "Money must >= 0")
    @Max(value = 1000000, message = "Money must <= 1000000")
    private int money;

    private Date birthday;
}
