package com.example.graduation_3.dto;

import com.example.graduation_3.common.Constast;
import com.sun.org.apache.bcel.internal.generic.LNEG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cy
 * @data 2020/5/9 - 11:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

   // private UserDTO user;

    private String nickName;

    private String grade;

    private String college;

    private String synopsis;

    private String email;

    private String image;

    private String account;

    private String role;

    public String getImage() {
        return Constast.IMAGE_GET_PATH + image;
    }

    public MemberDTO(Long userId, String nickName, String grade, String college, String synopsis, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.grade = grade;
        this.college = college;
        this.synopsis = synopsis;
        this.email = email;
    }
}
