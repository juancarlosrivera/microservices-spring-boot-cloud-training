package com.jc.rest.webservices.restfulwebservices.dao;

import com.jc.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "JC", new Date()));
        users.add(new User(2, "Adam", new Date()));
        users.add(new User(3, "Eve", new Date()));
    }

    public List<User> findAll() {

        return users;
    }

    public User save(User user) {

        if (user.getId() < 0) {
            user.setId(usersCount++);
        }
        users.add(user);
        return user;
    }

    public User findOne(int userId) {

        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int userId) {

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == userId) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }
}
