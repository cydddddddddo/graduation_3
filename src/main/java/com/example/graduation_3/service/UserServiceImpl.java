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
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名称获得用户实体
     * @param name
     * @return
     */
    @Override
    public UserDTO getUserByName(String name){
        return userMapper.getUserByName(name);
    }

    /**
     * 新增用户，并加密密码
     * @param user
     * @return
     */
    @Override
    public int addUser(UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_"+user.getRole());
        return userMapper.addUser(user);
    }

    /**
     * 通过id获得用户
     * @param id
     * @return
     */
    @Override
    public UserDTO getUserByid(Long id) {
        return userMapper.getUserByid(id);
    }
}
