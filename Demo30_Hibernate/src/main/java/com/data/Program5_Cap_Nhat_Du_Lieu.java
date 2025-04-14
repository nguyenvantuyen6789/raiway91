package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class Program5_Cap_Nhat_Du_Lieu {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // thêm, sửa, xoá cần dùng transaction (vì thay đổi dữ liệu)
            // truy vấn để hiển thị k cần transaction (vì k thay đổi dữ liệu)

            // begin transaction
            session.beginTransaction();

            // cập nhật:
            // b1. cần lấy entity theo id
            Query<Account> query = session.createQuery("FROM Account WHERE id = 6");
            Account account = query.getSingleResultOrNull();
            // b2. thay đổi dữ liệu
            if (account != null) {
                account.setFullName("Nguyen Van B");
                account.setAddress("Hue");
            }
            // b3. save lại
            session.save(account);

            // end transaction
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
        configuration.addAnnotatedClass(Account.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
