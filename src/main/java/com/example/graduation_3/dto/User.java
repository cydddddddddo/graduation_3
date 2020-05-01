package com.example.graduation_3.dto;

import java.io.Serializable;

/**
 * @author Cy
 * @data 2020/4/30 - 17:12
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String loginName;

    private String password;
    
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
