package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;
    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

    @Override
    public User findAll(Integer id) {
        return userDao.findAll(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public String findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void upLoadImage(User user) {
        userDao.upLoadImage(user);
    }


    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }

    @Override
    public User getOne(String email) {
        return userDao.getOne(email);
    }

    @Override
    public void upDatePassWord(User user) {
            userDao.upDatePassWord(user);
    }

    @Override
    public void upDateCode(User user) {
            userDao.upDateCode(user);
    }


}
