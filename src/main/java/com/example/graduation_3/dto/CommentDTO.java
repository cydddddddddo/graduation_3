package com.example.graduation_3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Cy
 * @data 2020/5/12 - 22:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long exchangeId;
    private Long userId;
    private String content;
    private MemberDTO member;
    private UserDTO user;
    private Timestamp sendDate;
}
