package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Scanner;

public class Program4_Gia_Tien {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            Scanner sc = new Scanner(System.in);
            int price = sc.nextInt();

            Query<Product> query = session.createQuery("FROM Product WHERE price > :price");
            query.setParameter("price", price);
            List<Product> products = query.list();

            products.forEach(product -> {
                System.out.println("Id: " + product.getId());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice());

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
        configuration.addAnnotatedClass(Product.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
