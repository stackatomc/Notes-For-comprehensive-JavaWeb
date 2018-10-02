package com.service;

import com.dao.UserDao;

import com.model.User;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MayerFang
 * @file helloWorld
 * @Description
 * @date 2018/10/1
 */
@Service
public class UserService {

    //@Resource(name="userDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUser(String id){
        return userDao.getUser(id);
    }

    public void modifyUser(User user){
        userDao.modifyUser(user);
    }

    public void deleteUser(String id){
        userDao.deleteUser(id);
    }
}
