package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age TINYINT)";
        try (Connection conn = util.getConnection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUsers = "DROP TABLE IF EXISTS users";
        try (Connection conn = util.getConnection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(dropUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";
        try (Connection conn = util.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id = ?";
        try (Connection conn = util.getConnection(); PreparedStatement PreparedStatement = conn.prepareStatement(removeUser)) {
            PreparedStatement.setLong(1, id);
            PreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String allUsers = "SELECT id, name, lastName, age FROM users";
        try (Connection conn = util.getConnection(); Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(allUsers)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        try (Connection conn = util.getConnection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
