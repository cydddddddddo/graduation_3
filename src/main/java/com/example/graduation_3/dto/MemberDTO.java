package com.example.graduation_3.dto;

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

    private String nickName;

    private String grade;

    private String college;

    private String synopsis;
}
