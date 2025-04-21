package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program2_Create {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            session.beginTransaction();
            // tạo address mới,có person mới
            // b1: tạo person và lưu person
            // b2: tạo address, set các thuộc tính cho address
            // cần address.setPerson(person ở bước 1
            // b3: save

            Person person = new Person();
            person.setFullName("Nguyen Van");
            person.setPhone("789");

            session.save(person);

            Address address = new Address();
            address.setStreet("Ho Tung Mau");
            address.setCity("HN");

            // code, lay address by id = 6
            // code, lay person by id = 8


            // khi set đối tượng(có chứa id=6), sau đó save thì id của
            // đối tượng sẽ được lưu vào khoá ngoại
            address.setPerson(person);

            session.save(address);

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
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Address.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
