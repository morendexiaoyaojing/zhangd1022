package com.qf.dao;

import com.qf.entity.User;

import java.util.List;

public interface UserDao {
    //显示个人信息
    public User findAll(Integer id);
    //登录
    public User findUser(User user);

    //注册
    public void addUser(User user);
    //注册所需要的邮箱是否重复
    public String findByEmail(String email);

    //更改资料
    public void updateUser(User user);
    //更改头像
    public void upLoadImage(User user);

    //密码安全页面的修改密码
    public void updatePassword(User user);



    //根据email查询信息
    public User getOne(String email);
    //根据email修改密码
    public void upDatePassWord(User user);
    //修改验证码
    public void upDateCode(User user);

}
