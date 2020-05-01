package com.example.graduation_3.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cy
 * @data 2020/4/30 - 17:19
 */

@Data
@NoArgsConstructor
public class ResultObj {
    public static final ResultObj  LOGIN_SUCCESS=new ResultObj(Constast.OK, "登陆成功");
    public static final ResultObj  LOGIN_ERROR_PASS=new ResultObj(Constast.ERROR, "登陆失败,密码错误");
    public static final ResultObj  LOGIN_ERROR_CODE=new ResultObj(Constast.ERROR, "登陆失败,验证码错误");
    public static final ResultObj  LOGIN_ERROR_NULL=new ResultObj(Constast.ERROR,"登陆失败，用户未注册");
    public static final ResultObj  LOGIN_ERROR_ROLE=new ResultObj(Constast.ERROR,"登陆失败，请检查身份信息");

    private Integer code;
    private String msg;

    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
