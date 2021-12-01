package com.example.service.impl;

import com.example.dao.impl.UserDaoImpl;
import com.example.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("userservice is running ...");
    }

    public void setUserDao(UserDaoImpl userDao) {
    }
}
