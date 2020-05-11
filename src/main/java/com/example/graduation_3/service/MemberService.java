package com.example.graduation_3.service;

import com.example.graduation_3.dto.MemberDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Cy
 * @data 2020/5/9 - 11:56
 */
public interface MemberService {

    MemberDTO getMemberById(Long userId);
}
