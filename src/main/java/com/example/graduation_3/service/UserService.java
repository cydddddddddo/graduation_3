package com.example.graduation_3.service;


import com.example.graduation_3.dto.User;
import com.example.graduation_3.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Cy
 * @data 2020/4/30 - 22:11
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }

}
