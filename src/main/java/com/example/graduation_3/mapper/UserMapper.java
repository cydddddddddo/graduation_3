package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(UserDTO user);

    /**
     * 新增账户
     * @param member
     * @return
     */
    int addMember(MemberDTO member);

    /**
     * 获得信息
     * @param user
     * @return
     */
    List<UserDTO> getUserId(UserDTO user);

    /**
     * 通过id获得用户
     * @param id
     * @return
     */
    UserDTO getUserByid(@Param("id")Long id);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int changeUser(UserDTO user);

    /**
     * 获得用户列表
     * @return
     */
    List<MemberDTO> getUserList(@Param("page")Integer page,@Param("limit")Integer limit,@Param("key")String key);

    /**
     * 通过账户id获得用户id
     * @param id
     * @return
     */
    Long getUserIdByMemberId(@Param("id")Long id);
    /**
     * 获得用户数
     * @param key
     * @return
     */
    Long getCount(@Param("key")String key);

    /**
     * 修改账户信息
     * @param member
     * @return
     */
    int amendMember(MemberDTO member);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int amendUser(UserDTO user);

    int deleteUser(@Param("id")Long id);

    int deleteMember(@Param("id")Long id);

}
