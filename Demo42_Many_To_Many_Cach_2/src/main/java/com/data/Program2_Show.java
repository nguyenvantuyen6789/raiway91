package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program2_Show {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            Query<MonHoc> query = session.createQuery("FROM MonHoc");
            List<MonHoc> list = query.list();
            list.forEach(monHoc -> {
                System.out.println("====");
                System.out.println("Mã môn học: " + monHoc.getId());
                System.out.println("Tên môn học: " + monHoc.getTenMon());
                System.out.println("Số giờ: " + monHoc.getSoGio());

                List<SinhVien> sinhViens = monHoc.getSinhViens();
                if (sinhViens.isEmpty()) {
                    System.out.println("Không có sinh viên nào học môn này");
                } else {
                    sinhViens.forEach(sinhVien -> {
                        System.out.println("Mã sinh viên: " + sinhVien.getId());
                        System.out.println("Tên sinh viên: " + sinhVien.getFullName());
                    });
                }
            });


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        configuration.addAnnotatedClass(MonHoc.class);
        configuration.addAnnotatedClass(SinhVien.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

}
