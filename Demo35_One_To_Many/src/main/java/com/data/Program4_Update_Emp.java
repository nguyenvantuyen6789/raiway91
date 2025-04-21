package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class Program4_Update_Emp {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // lấy emp id = 2
            Query<Employee> query = session.createQuery("FROM Employee WHERE id = 2");
            Employee employee = query.getSingleResultOrNull();

            // return first
            if (employee == null) {
                // dừng chương trình
                System.out.println("Khong tim thay emp id = 2");
                return;
            }

            employee.setPhone("999666");

            // lấy department id = 2
            Query<Department> query2 = session.createQuery("FROM Department WHERE id = 200");
            Department department = query2.getSingleResultOrNull();
            employee.setDepartment(null);

            // thêm: cần transaction
            session.beginTransaction();

            // lưu emp
            session.save(employee);
            session.getTransaction().commit();
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
