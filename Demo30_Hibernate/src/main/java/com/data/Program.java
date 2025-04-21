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

            // tạo truy vấn hiển thị dữ liệu
            // FROM Account: Chứ A phải viết hoa, trùng với Entity Account
            // hibernate lấy dữ liệu Account

            // hibernate: không dùng select *
            // hibernate: dùng với entity, k dùng với tên bảng
            // sql                          hibernate (hql: hibernate query language)
            // SELECT * FROM account        FROM Account
            // SELECT * FROM account        FROM account
            //          WHERE address = 'HP'        WHERE address = 'HP'
            // SELECT * FROM account        FROM account
            //          WHERE id = 4            WHERE id=4
//            Query<Account> query = session.createQuery("FROM Account");
//            Query<Account> query = session.createQuery("FROM Account WHERE address='HP'");
            Query<Account> query = session.createQuery("FROM Account");
            List<Account> accounts = query.getResultList();

            // a: là phần tử trong list accounts
            accounts.forEach(a -> {
                // mỗi lần 1 phần tử
                int value1 = a.getId();
                System.out.println(value1);

                String address = a.getAddress();
                System.out.println(address);

                String fullName = a.getFullName();
                System.out.println(fullName);
                int ageNumber = a.getAgeNumber();
                System.out.println(ageNumber);
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
        configuration.addAnnotatedClass(Account.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
