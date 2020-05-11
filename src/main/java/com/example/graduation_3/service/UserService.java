package com.example.graduation_3.service;

import com.example.graduation_3.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Cy
 * @data 2020/5/2 - 15:05
 */

public interface UserService {

    UserDTO getUserByName(String name);

    /*String getNickNameById(Long id);*/

    int addUser(UserDTO user);
}

