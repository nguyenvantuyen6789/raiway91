package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory sessionFactory = buildSessionFactory();
            session = sessionFactory.openSession();

//            Query<Department> query = session.createQuery("FROM Department", Department.class);
//            List<Department> departments = query.list();

//            departments.forEach(department -> {
//                System.out.println("=========");
//                System.out.println("Department ID: " + department.getId());
//                System.out.println("Department Name: " + department.getName());
//
//                List<Employee> employees = department.getEmployees();
//                employees.forEach(employee -> {
//                    System.out.println("Employee Name: " + employee.getName());
//                });
//            });

            session.beginTransaction();

            Query<Department> query = session.createQuery("FROM Department WHERE id = 1", Department.class);
            Department department = query.getSingleResultOrNull();

            Employee employee = new Employee();
            employee.setName("Nguyen Van E");

            department.setEmployee(employee);

            session.save(department);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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
