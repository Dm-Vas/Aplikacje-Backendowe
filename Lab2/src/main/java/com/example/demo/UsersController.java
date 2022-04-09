package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UsersController {
    private final Object locker = new Object();
    private final Map<Integer, UserEntity> users = new LinkedHashMap<>();

    @PostConstruct
    private void onCreate() {
        users.put(1, new UserEntity("Mike", 30));
        users.put(2, new UserEntity("Katty", 27));
        users.put(3, new UserEntity("Kyle", 20));
    }

    @RequestMapping("/users")
    @ResponseBody
    // http://localhost:1000/users
    public Object getUsers() {
        return users;
    }

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    // http://localhost:1000/users/1/get
    public Object getUser(@PathVariable int id) {
        synchronized (this.locker) {
            return this.users.get(id);
        }
    }

    @RequestMapping("/users/{id}/remove")
    @ResponseBody
    // http://localhost:1000/users/1/remove
    UserEntity removeUser(@PathVariable("id") int id) {
        return users.remove(id);
    }

    @RequestMapping("/users/add")
    @ResponseBody
    // http://localhost:1000/users/add?name=John&age=23
    UserEntity addUser(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        UserEntity user = new UserEntity(name, age);
        users.put(users.size() + 1, user);
        return user;
    }
}
