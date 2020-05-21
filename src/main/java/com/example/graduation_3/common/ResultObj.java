package com.example.graduation_3.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cy
 * @data 2020/4/30 - 17:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    public static final ResultObj  LOGIN_SUCCESS=new ResultObj(Constast.OK, "登陆成功");
    public static final ResultObj  LOGIN_ERROR_PASS=new ResultObj(Constast.ERROR, "登陆失败,密码错误");
    public static final ResultObj  LOGIN_ERROR_CODE=new ResultObj(Constast.ERROR, "登陆失败,验证码错误");
    public static final ResultObj  LOGIN_ERROR_NULL=new ResultObj(Constast.ERROR,"登陆失败，用户未注册");
    public static final ResultObj  LOGIN_ERROR_ROLE=new ResultObj(Constast.ERROR,"登陆失败，请检查身份信息");
    public static final ResultObj DELETE_SUCCESS=new ResultObj(Constast.OK,"删除成功");
    public static final ResultObj DELETE_FAILED=new ResultObj(Constast.ERROR,"删除失败");

    public static final ResultObj AMEND_SUCCESS=new ResultObj(Constast.OK,"提交成功！");
    public static final ResultObj AMEND_FAILED=new ResultObj(Constast.ERROR,"提交失败，请联系管理员。");

    public static final ResultObj CHANGE_SUCCESS=new ResultObj(Constast.OK,"修改成功。");
    public static final ResultObj CHANGE_FAILED=new ResultObj(Constast.ERROR,"修改失败，请联系管理员。");
    public static final ResultObj CHANGE_OLDPWD_FAILED=new ResultObj(Constast.ERROR,"修改失败，旧密码错误。");
    public static final ResultObj CHANGE_NEWPWD_FAILED=new ResultObj(Constast.NEW_REAL,"修改失败，前后密码一致。");
    public static final ResultObj CHANGE_REALPWD_FAILED=new ResultObj(Constast.NEW_REAL,"修改失败，确认密码不一致。");
    //public static final ResultObj CHANGE_NULL_FAILED=new ResultObj(Constast.NEW_REAL,"修改失败，不能为空。");

    public static final ResultObj VERIFY_SUCCESS=new ResultObj(Constast.OK,"密码正确，解锁成功");
    public static final ResultObj VERIFY_FAILED=new ResultObj(Constast.ERROR,"密码错误，解锁失败");

    public static final ResultObj AMEND_USER_SUCCESS = new ResultObj(Constast.OK,"修改成功");
    public static final ResultObj AMEND_USER_FAILED = new ResultObj(Constast.ERROR,"修改失败");
    public static final ResultObj AMEND_ROLE_FAILED = new ResultObj(Constast.ERROR,"权限格式错误");


    private Integer code;
    private String msg;

    /*public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }*/
}
