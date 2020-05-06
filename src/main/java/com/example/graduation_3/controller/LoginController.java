package com.example.graduation_3.controller;

import com.example.graduation_3.common.ResultObj;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cy
 * @data 2020/4/30 - 23:45
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController{

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/login")
    @ResponseBody
    public ResultObj login(@RequestParam("name")String name,@RequestParam("role")String role,@RequestParam("code")String code,Model model){
        UserDTO user = userServiceImpl.getUserByName(name);
        /**这里可以利用前端传入的codeID到数据库中查询相应验证码，验证是否正确
         *String tempCode = codeService.getCodeById(codeId);*/
        //此处的验证码可以改为↑.
        if (role.equals(user.getRole().substring(5))){
            if (code.equals("jgmxj")){
                return ResultObj.LOGIN_SUCCESS;
            }else {
                return ResultObj.LOGIN_ERROR_CODE;
            }
        }else {
            return ResultObj.LOGIN_ERROR_ROLE;
        }
    }


    @RequestMapping("login2")
    @ResponseBody
    public ResultObj login2(@RequestParam("name")String name,@RequestParam("password")String password,
                           @RequestParam("code")String code,@RequestParam("role")String role){
        UserDTO userDTO= userServiceImpl.getUserByName(name);
        //这里可以利用前端传入的codeID到数据库中查询相应验证码，验证是否正确
        //String tempCode = codeService.getCodeById(codeId);
        //此处的验证码可以改为↑.
        if (code.equals("jgmxj")){
            if (userDTO !=null){
                //身份信息验证
                if(role.equals(userDTO.getRole().substring(5))){
                    //密码错误
                        return ResultObj.LOGIN_ERROR_PASS;
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
