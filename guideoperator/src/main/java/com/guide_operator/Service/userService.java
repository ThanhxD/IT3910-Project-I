package com.guide_operator.Service;

import com.guide_operator.Dao.MysqlUserDao;
import com.guide_operator.Dao.UserDao;
import com.guide_operator.Entities.Guide;
import com.guide_operator.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class userService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MysqlUserDao mysqlUserDao;

    public  Collection<User> getAllUser(){
        return this.userDao.getAllUser();
    }

    public User getUserById(int id){
        return this.userDao.getUserById(id);
    }

    public User getUserByUsername(String username){
        return this.userDao.getUserByUsername(username);
    }

    public void removeUserById(int id){
         this.userDao.removeUserById(id);
    }

    public void updateUser(User user){
         this.userDao.updateUser(user);
    }

    public void  insertUsertoDb(User user){
        this.userDao.insertUsertoDb(user);
    }

    public void insertGuidetoDb(Guide guide) {
        this.mysqlUserDao.insertGuidetoDb(guide);
    }
}
