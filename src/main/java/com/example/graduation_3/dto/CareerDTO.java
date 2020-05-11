package com.example.graduation_3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Cy
 * @data 2020/5/9 - 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nickName;
    private String companyName;
    private String grade;
    private String position;
    private String attach;
    private Date beginYear;
    private Date endYear;
}
