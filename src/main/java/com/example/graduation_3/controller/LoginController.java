package com.example.graduation_3.controller;

import com.example.graduation_3.common.ResultObj;
import com.example.graduation_3.dto.User;
import com.example.graduation_3.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Cy
 * @data 2020/4/30 - 23:45
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public ResultObj login(@RequestParam("name")String name,@RequestParam("password")String password,
                           @RequestParam("code")String code,@RequestParam("role")String role){
        User user = null;
        user = userService.getUserByName(name);
        /**这里可以利用前端传入的codeID到数据库中查询相应验证码，验证是否正确
        *String tempCode = codeService.getCodeById(codeId);*/
        //此处的验证码可以改为↑.
        if (code.equals("jgmxj")){
            if (user!=null){
                //身份信息验证
                if(role.equals(user.getRole())){
                    //密码验证
                    if (password.equals(user.getPassword())){
                        return ResultObj.LOGIN_SUCCESS;
                    }else {
                        return ResultObj.LOGIN_ERROR_PASS;
                    }
                }else {
                    return ResultObj.LOGIN_ERROR_ROLE;
                }
            }else {
                //没被注册
                return ResultObj.LOGIN_ERROR_NULL;
            }
        }else {
            return ResultObj.LOGIN_ERROR_CODE;
        }
    }
}
