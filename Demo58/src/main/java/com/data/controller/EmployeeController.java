package com.data.controller;

import com.data.dto.EmployeeDTO;
import com.data.entity.Department;
import com.data.entity.Employee;
import com.data.repository.DepartmentRepository;
import com.data.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepo;

    public EmployeeController(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("employees")
    public ResponseEntity<?> getAll() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId(employee.getId());
            employeeDTO.setFullName(employee.getFullName());

            employeeDTOS.add(employeeDTO);
        });

        // cac ban giai lao 10p
        return ResponseEntity.ok(employeeDTOS);
    }
}
