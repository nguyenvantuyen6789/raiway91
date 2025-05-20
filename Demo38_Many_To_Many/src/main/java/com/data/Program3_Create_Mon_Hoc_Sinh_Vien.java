package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Program3_Create_Mon_Hoc_Sinh_Vien {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // b1. tạo 1 dòng trong bảng: monhocsinhvien (MonHocSinhVien)
            // b2. mysql: mon_hoc_id: 1, sinh_vien_id: 1
            //          => MonHoc(id=1), SinhVien(id=1)

            // Hibernate:
            // b1. tạo đối tượng MonHocSinhVien
            // b2. set cho MonHocSinhVien: MonHoc(id=1), SinhVien(id=1)
            // b3. lưu đối tượng MonHocSinhVien vào db

            MonHocSinhVien monHocSinhVien = new MonHocSinhVien();
            MonHoc monHoc = new MonHoc();
            monHoc.setId(1);

            SinhVien sinhVien = new SinhVien();
            sinhVien.setId(1);

            monHocSinhVien.setMonHoc(monHoc);
            monHocSinhVien.setSinhVien(sinhVien);

            // b4. bắt đầu transaction
            session.beginTransaction();

            // b5. lưu đối tượng MonHocSinhVien vào db
            session.save(monHocSinhVien);
            // b6. commit transaction
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
        configuration.addAnnotatedClass(MonHoc.class);
        configuration.addAnnotatedClass(SinhVien.class);
        configuration.addAnnotatedClass(MonHocSinhVien.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
