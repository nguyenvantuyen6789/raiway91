package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program2_List_Emp {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // get employee
//            Query<Employee> query = session.createQuery("FROM Employee");
//            List<Employee> employees = query.list();
//
//            employees.forEach(employee -> {
//                System.out.println("Em Id: " + employee.getId());
//                System.out.println("Em Name: " + employee.getEmployeeName());
//                System.out.println("Em Phone: " + employee.getPhone());
//
//                Department department = employee.getDepartment();
//                if (department != null) {
//                    System.out.println("Department Name: " + department.getDepartmentName());
//                } else {
//                    System.out.println("Nhan vien nay k co phong ban");
//                }
//            });

            // get department
            Query<Department> query = session.createQuery("FROM Department");
            List<Department> departments = query.list();

            departments.forEach(department -> {
                System.out.println("Department Id: " + department.getId());
                System.out.println("Department Name: " + department.getDepartmentName());

                List<Employee> employees = department.getEmployees();
                if (employees != null) {
                    employees.forEach(employee -> {
                        System.out.println("Employee Id: " + employee.getId());
                        System.out.println("Employee Name: " + employee.getEmployeeName());
                    });
                } else {
                    System.out.println("Phong ban nay khong co nhan vien");
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // hàm gọi đến cấu file cấu hình hibernate.cfg.xml - > dbname, tài khoản root
    private static SessionFactory buildSessionFactory() {
        // load db info
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // add entity
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Employee.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
