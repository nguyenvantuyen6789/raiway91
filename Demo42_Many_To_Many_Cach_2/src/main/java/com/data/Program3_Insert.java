package com.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
// thực thể môn học sinh viên: int diem, String danhGia
public class Program3_Insert {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            Query<MonHoc> query = session.createQuery("FROM MonHoc WHERE id = 3", MonHoc.class);
            MonHoc monHoc = query.getSingleResultOrNull();
            if (monHoc != null) {
                // tạo ra 3 đối tượng sinh viên có id 1,2,3
                SinhVien sinhVien1 = new SinhVien();
                sinhVien1.setId(1);
                SinhVien sinhVien2 = new SinhVien();
                sinhVien2.setId(2);
                SinhVien sinhVien3 = new SinhVien();
                sinhVien3.setId(3);

                // tạo list rồi add 3 sv
                List<SinhVien> sinhViens = new ArrayList<>();
                sinhViens.add(sinhVien1);
                sinhViens.add(sinhVien2);
                sinhViens.add(sinhVien3);

                // set list sinh viên cho môn học
                monHoc.setSinhViens(sinhViens);

                // bắt đầu transaction
                session.beginTransaction();
                // lưu môn học
                session.save(monHoc);
                // commit transaction
                session.getTransaction().commit();
            } else {
                System.out.println("MonHoc not found");
            }


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
