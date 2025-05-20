package com.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {

    private int id;

    private String departmentName;

    List<EmployeeDTO> employeeDTOS;
}
