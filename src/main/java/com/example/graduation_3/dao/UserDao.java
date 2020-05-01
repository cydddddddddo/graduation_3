package com.example.graduation_3.dao;

import com.example.graduation_3.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Cy
 * @data 2020/4/30 - 17:17
 */
@Mapper
public interface UserDao {

    User getUserByName(@Param("name")String name);
}
