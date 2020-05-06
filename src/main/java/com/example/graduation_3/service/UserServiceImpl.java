package com.example.graduation_3.service;


import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Cy
 * @data 2020/4/30 - 22:11
 */
@Service
public class UserServiceImpl {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名称获得用户实体
     * @param name
     * @return
     */
    public UserDTO getUserByName(String name){
        return userMapper.getUserByName(name);
    }

    /**
     * 通过用户id获得昵称
     * @param id
     * @return
     */
    public String getNickNameById(@Param("id")Long id){
        return userMapper.getNickNameById(id);
    }

    /**
     * 新增用户，并加密密码
     * @param user
     * @return
     */
    public int addUser(UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_"+user.getRole());
        return userMapper.addUser(user);
    }
}
