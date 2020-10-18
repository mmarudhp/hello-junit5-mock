package com.hello.mock;

public class UserRepository {

    public void save(User user) {
        System.out.println("Save User");
    }

    public User get() {
        return new User("Marudhupandian Maruthan", 35);
    }
}
