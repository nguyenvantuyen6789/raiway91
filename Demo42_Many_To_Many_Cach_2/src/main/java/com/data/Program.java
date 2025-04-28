package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Program {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // tạo môn học
            MonHoc monHoc1 = new MonHoc();
            monHoc1.setTenMon("Lập trình Java");
            monHoc1.setSoGio(30);
            monHoc1.setSoBuoi(15);

            // tạo moonn học
            MonHoc monHoc2 = new MonHoc();
            monHoc2.setTenMon("Lập trình C#");
            monHoc2.setSoGio(30);
            monHoc2.setSoBuoi(15);

            // tạo sinh viên
            SinhVien sinhVien1 = new SinhVien();
            sinhVien1.setMaSinhVien("SV001");
            sinhVien1.setFullName("Nguyễn Văn A");
            sinhVien1.setGioiTinh("Nam");

            // tạo sinh viên
            SinhVien sinhVien2 = new SinhVien();
            sinhVien2.setMaSinhVien("SV002");
            sinhVien2.setFullName("Nguyễn Văn B");
            sinhVien2.setGioiTinh("Nam");

            session.beginTransaction();

            // session save sinh viên, môn học
            session.save(sinhVien1);
            session.save(sinhVien2);

            session.save(monHoc1);
            session.save(monHoc2);

            session.getTransaction().commit();


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
