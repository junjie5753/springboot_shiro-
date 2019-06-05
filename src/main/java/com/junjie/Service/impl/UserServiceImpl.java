package com.junjie.Service.impl;

import com.junjie.Service.UserService;
import com.junjie.dao.UserDao;
import com.junjie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUser(String username) {
        return userDao.findUserByUsername(username);
    }
}
