package com.example.graduation_3.service;

import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/2 - 15:05
 */

public interface UserService {

    UserDTO getUserByName(String name);

    int addUser(UserDTO user);

    UserDTO getUserByid(Long id);

    int changeUser(UserDTO user);

    List<MemberDTO> getUserList(Integer page,Integer limit,String key);

    Long getCount(String key);

    int amendMember(MemberDTO member);

    int amendUser(UserDTO user);

    List<UserDTO> getUserId(UserDTO user);

    int addMember(MemberDTO member);

    Long getUserIdByMemberId(Long id);

    int deleteUser(Long id);

    int deleteMember(Long id);
}

