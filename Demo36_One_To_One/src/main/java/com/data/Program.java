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

            Query<Person> query = session.createQuery("FROM Person");
            List<Person> people = query.list();

            people.forEach(person -> {
                System.out.println("==== Thong tin Person ====");
                // in đối tượng, sẽ tự động chạy vào hàm toString
                // in person bên dưới: Lỗi
//                System.out.println(person);

                System.out.println(person.getId());
                System.out.println(person.getFullName());
                System.out.println(person.getEmail());

                Address address = person.getAddress();
                if (address != null) {
                    System.out.println("Street: " + address.getStreet());
                    System.out.println("City: " + address.getCity());
                } else {
                    System.out.println("Person nay k co dia chi");
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
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Address.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
