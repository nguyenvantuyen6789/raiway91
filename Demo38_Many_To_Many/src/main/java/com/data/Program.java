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

            Query<MonHoc> query = session.createQuery("FROM MonHoc");
            List<MonHoc> monHocs = query.list();

            monHocs.forEach(monHoc -> {
                System.out.println("==================");
                System.out.println("Id: " + monHoc.getId());
                System.out.println("Ten Mon: " + monHoc.getTenMon());

                List<MonHocSinhVien> monHocSinhViens = monHoc.getMonHocSinhViens();
                monHocSinhViens.forEach(monHocSinhVien -> {
                    SinhVien sinhVien = monHocSinhVien.getSinhVien();

                    if (sinhVien != null) {
                        System.out.println("Id trong sinh vien: " + sinhVien.getId());
                        System.out.println("Full Name trong sinh vien: " + sinhVien.getFullName());
                    } else {
                        System.out.println("Mon hoc nay khong co sv nao hoc");
                    }
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
        configuration.addAnnotatedClass(MonHoc.class);
        configuration.addAnnotatedClass(SinhVien.class);
        configuration.addAnnotatedClass(MonHocSinhVien.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
