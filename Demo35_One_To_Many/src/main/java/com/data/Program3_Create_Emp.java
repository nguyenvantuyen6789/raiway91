package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program3_Create_Emp {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();


            // thêm: cần transaction
            session.beginTransaction();

            // b1. tạo đối tượng emp
            Employee employee = new Employee();
            employee.setEmployeeName("Pham Thanh");
            // b2. lấy phòng ban IT có id là 2
            Query<Department> query = session.createQuery("FROM Department WHERE id = 2");
            Department department = query.getSingleResultOrNull();
            if (department == null) {
                // dừng chương trình
                return;
            }
            // b3. set emp trên với phòng ban mới lấy ra
            employee.setDepartment(department);
            // b4. save emp: save được emp và id của phòng ban IT là 2
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
