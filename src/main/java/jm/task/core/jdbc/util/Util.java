package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import java.sql.*;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/pp_hb_trial";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ihateall0fth1s";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pp_hb_trial");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "ihateall0fth1s");
                prop.setProperty("hibernate.show_sql", "true");
                prop.setProperty("hibernate.hbm2ddl.auto", "update");

                Configuration cfg = new Configuration();
                cfg.addAnnotatedClass(User.class);
                cfg.addProperties(prop);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties());
                sessionFactory = cfg.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

   public static Connection getConnection() {
       try {
            Class.forName(DRIVER);
           return DriverManager.getConnection(URL, USERNAME, PASSWORD);
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException("Подключение не удалось", e);
        }
    }

}