package com.data.controller;

import com.data.dto.DepartmentDTO;
import com.data.dto.EmployeeDTO;
import com.data.entity.Department;
import com.data.entity.Employee;
import com.data.repository.DepartmentRepository;
import com.data.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentRepository departmentRepo;

    private DepartmentService departmentService;

    public DepartmentController(DepartmentRepository departmentRepo,
                                DepartmentService departmentService) {
        this.departmentRepo = departmentRepo;
        this.departmentService = departmentService;
    }

    @GetMapping("departments")
    public ResponseEntity<?> getAll() {
        // EAGER: chay xong dòng này thì hibernate
        // sinh ra 3 câu truy vấn
        // LAZY: chay xong dòng này thì hibernate
        // sinh ra 1 cau
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        departments.forEach(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartmentName());

            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            List<Employee> employees = department.getEmployees();
            for (int i = 0; i < employees.size(); i++) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(employees.get(i).getId());
                employeeDTO.setFullName(employees.get(i).getFullName());

                employeeDTOS.add(employeeDTO);
            }

            departmentDTO.setEmployeeDTOS(employeeDTOS);

            departmentDTOS.add(departmentDTO);

            // sinh ra 2 câu truy vấn
//            List<Employee> employees = department.getEmployees();
//            System.out.println(employees.size());
        });

        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping("departments/getByDepartmentName/{departmentName}")
    public ResponseEntity<?> getByDepartmentName(@PathVariable String departmentName) {
        List<Department> departments = departmentService
                .getListDepartmentByDepartmentName(departmentName);
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        departments.forEach(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartmentName());

            departmentDTOS.add(departmentDTO);
        });

        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping("getData/{departmentName}/{address}")
    public ResponseEntity<?> getData(@PathVariable String departmentName,
                                     @PathVariable String address) {
        List<Department> departments = departmentRepo
                .findByDepartmentNameAndAddress(departmentName, address);

        return ResponseEntity.ok(departments.size());
    }
}
