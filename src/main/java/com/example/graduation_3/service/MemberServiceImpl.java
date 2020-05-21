package com.example.graduation_3.service;

import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cy
 * @data 2020/5/9 - 11:56
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDTO getMemberById(Long userId) {
        return memberMapper.getMemberById(userId);
    }

    @Override
    public Integer amendMember(String email, String synopsis, Long userId,String nickName,String grade,String college,String image) {
        return memberMapper.amendMember(email, synopsis, userId,nickName,grade,college,image);
    }
}
