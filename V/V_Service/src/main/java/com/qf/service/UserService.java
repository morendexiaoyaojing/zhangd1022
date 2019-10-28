package com.qf.service;

import com.qf.entity.User;

import java.util.List;

public interface UserService {

    public User findUser(User user);

    public User findAll(Integer id);

    public void addUser(User user);

    public String findByEmail(String email);

    public void updateUser(User user);

    public void upLoadImage(User user);
    public void updatePassword(User user);

    public User getOne(String email);
    //根据email修改密码
    public void upDatePassWord(User user);
    //修改验证码
    public void upDateCode(User user);
}
