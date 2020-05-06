package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Cy
 * @data 2020/4/30 - 17:17
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 通过用户名获得用户
     * @param name
     * @return
     */
    UserDTO getUserByName(@Param("name")String name);

    int addUser(UserDTO user);

    String getNickNameById(@Param("id")Long id);
}
