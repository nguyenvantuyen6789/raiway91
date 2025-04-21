package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            Query<Department> query = session.createQuery("FROM Department");
            List<Department> departments = query.list();

            departments.forEach(department -> {
                System.out.println("Id: " + department.getId());
                System.out.println("Department Name: " + department.getDepartmentName());
                System.out.println("Address: " + department.getAddress());

                // b2. lấy 1 list employee trong department
                List<Employee> employees = department.getEmployees();
                // b3. foreach in ra list employee
                employees.forEach(employee -> {
                    System.out.println("Em Id: " + employee.getId());
                    System.out.println("Em Name: " + employee.getEmployeeName());
                    System.out.println("Em Phone: " + employee.getPhone());
                });
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
