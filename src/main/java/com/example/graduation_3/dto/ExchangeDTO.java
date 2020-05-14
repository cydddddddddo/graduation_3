package com.example.graduation_3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Cy
 * @data 2020/5/12 - 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    //数据库中对应的参数
    private Long id;
    private Long userId;
    private String receiveGrade;
    private String receiveRole;
    private String receiveCollege;
    private String title;
    private String message;
    private Timestamp sendDate;
    private Timestamp amendDate;
    //前端所需要的其他值，处理后加入
    private String nickName;
    private String sendGrade;
    private String sendRole;
    private String sendCollege;
    private Long commentCount;
    private String changeDate;
}
