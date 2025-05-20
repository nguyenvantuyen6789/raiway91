package com.data.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private int id;

    private String fullName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
