package com.test02.dao;

import com.test02.projo.User;

import java.util.List;

public interface UserDao {
    //新增
    public void saveUser(User user);

    //删除
    public void removeUser(String id);

    //修改
    public void updateUser(User user);

    //根据编号查询
    public  User findById(String id);

    //查询所有
    public List<User> findAll();
}
