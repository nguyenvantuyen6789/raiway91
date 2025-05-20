package com.data.controller;

import com.data.entity.Department;
import com.data.entity.Employee;
import com.data.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentRepository departmentRepo;

    public DepartmentController(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("departments")
    public ResponseEntity<?> getAll() {
        // EAGER: chay xong dòng này thì hibernate
        // sinh ra 3 câu truy vấn
        // LAZY: chay xong dòng này thì hibernate
        // sinh ra 1 cau
        List<Department> departments = departmentRepo.findAll();
        departments.forEach(department -> {
            System.out.println(department.getDepartmentName());

            // sinh ra 2 câu truy vấn
//            List<Employee> employees = department.getEmployees();
//            System.out.println(employees.size());
        });

        return ResponseEntity.ok(departments);
    }
}
