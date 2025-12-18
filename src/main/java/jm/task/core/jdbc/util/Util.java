package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/pp_hb_trial";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ihateall0fth1s";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Подключение не удалось", e);
        }

    }
}