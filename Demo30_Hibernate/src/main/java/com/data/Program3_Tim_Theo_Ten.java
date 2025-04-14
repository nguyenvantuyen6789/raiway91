
import com.data.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Program3_Tim_Theo_Ten {
    public static void main(String[] args) {
        // session: tạo phiên làm việc với hibernate
        Session session = null;
        try {
            // tạo session
            session = buildSessionFactory().openSession();

            // dùng : để tạo param
            Query<Account> query = session.createQuery("FROM Account WHERE fullName = :fullName");
            // truyền dữ liệu cho param
            query.setParameter("fullName", "Nguyen Ngoc");

            List<Account> accounts = query.list();
            System.out.println("Size accounts: " + accounts.size());

            accounts.forEach(account -> {
                System.out.println("====");
                System.out.println("Id: " + account.getId());
                System.out.println("Full name: " + account.getFullName());
                System.out.println("Address: " + account.getAddress());
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
