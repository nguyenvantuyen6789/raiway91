package com.data.controller;

import com.data.entity.Employee;
import com.data.repository.EmployeeRepository;
import com.data.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    private EmployeeRepository employeeRepo;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeRepository employeeRepo) {
        this.employeeService = employeeService;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("employee-list")
    public String getAll(Model model) {
        // lấy list employee in ra ở đây, giải lao 10p
        List<Employee> employees = employeeService.getAll();
        // trả về view
        model.addAttribute("employees", employees);

        return "employee_list";
    }

    @GetMapping("employee/{id}")
    public String getById(@PathVariable int id,
                          Model model) {
        Employee employee = employeeRepo.findById(id).get();
        // trả về view
        model.addAttribute("employee", employee);

        return "employee_detail";
    }

    @GetMapping("employee-delete/{id}")
    public String delete(@PathVariable int id) {
        // lấy employee
        Employee employee = employeeRepo.findById(id).get();
        // xoá employee
        employeeRepo.delete(employee);
        System.out.println("Delete employee");
        System.out.println(employee);

        return "redirect:/employee-list";
    }

    @GetMapping("employee-add")
    public String add(Model model) {
        return "employee_add";
    }

    @PostMapping("employee-save")
    public String save(@ModelAttribute Employee employee) {
        System.out.println("employee");
        System.out.println(employee);

        return "redirect:/employee-list";
    }
}
