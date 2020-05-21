package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Cy
 * @data 2020/5/9 - 11:54
 */
@Repository
@Mapper
public interface MemberMapper {
    /**
     * 根据用户id获得用户详情
     * @param userId
     * @return
     */
    MemberDTO getMemberById(@Param("userId")Long userId);

    Integer amendMember(@Param("email")String email,@Param("synopsis")String synopsis,@Param("userId")Long userId
                        ,@Param("nickName")String nickName,@Param("grade")String grade,@Param("college")String college
                        ,@Param("image")String image);
}
