package com.example.graduation_3.service;


import com.example.graduation_3.common.Constast;
import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!"ROLE_".equals(user.getRole().substring(0,5))){
            user.setRole("ROLE_"+user.getRole());
        }
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

    @Override
    public int changeUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!"ROLE_".equals(user.getRole().substring(0,5))){
            user.setRole("ROLE_"+user.getRole());
        }
        int num = userMapper.changeUser(user);
        return num;
    }

    @Override
    public List<MemberDTO> getUserList(Integer page,Integer limit,String key) {
        return userMapper.getUserList(page, limit, key);
    }

    @Override
    public Long getCount(String key) {
        return userMapper.getCount(key);
    }

    @Override
    public int amendMember(MemberDTO member) {
        return userMapper.amendMember(member);
    }

    @Override
    public int amendUser(UserDTO user) {
        return userMapper.amendUser(user);
    }

    @Override
    public List<UserDTO> getUserId(UserDTO user) {
        return userMapper.getUserId(user);
    }

    @Override
    public int addMember(MemberDTO member) {
        if (!member.getGrade().endsWith(Constast.GRAGE_END)){
            member.setGrade(member.getGrade()+Constast.GRAGE_END);
        }
        return userMapper.addMember(member);
    }

    @Override
    public Long getUserIdByMemberId(Long id) {
        return userMapper.getUserIdByMemberId(id);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int deleteMember(Long id) {
        return userMapper.deleteMember(id);
    }
}
