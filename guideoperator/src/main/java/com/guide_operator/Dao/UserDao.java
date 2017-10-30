package com.guide_operator.Dao;

import com.guide_operator.Entities.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAllUser();

    User getUserById(int id);

    User getUserByUsername(String username);

    void removeUserById(int id);

    void updateUser(User user);

    void  insertUsertoDb(User user);
}
