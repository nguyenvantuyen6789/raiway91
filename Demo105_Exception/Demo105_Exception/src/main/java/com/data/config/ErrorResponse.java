package com.data.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @NonNull
    private String message;

    @NonNull
    private Integer code;

    @NonNull
    private String moreInformation;
}
