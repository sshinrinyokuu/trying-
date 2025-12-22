package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        List<User> list = new ArrayList<>();
        User user1 = new User("Vitya", "Rblbalka", (byte) 18);
        User user2 = new User("Sanya", "Anigilyatornaya", (byte) 22);
        User user3 = new User("Andruha", "Betonomeshalka", (byte) 20);
        User user4 = new User("Mitya", "Trankvilizator", (byte) 33);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> usersDB = userService.getAllUsers();
        for (User user : usersDB) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
