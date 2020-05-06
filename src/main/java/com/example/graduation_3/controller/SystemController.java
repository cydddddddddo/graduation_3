package com.example.graduation_3.controller;

import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cy
 * @data 2020/4/30 - 16:51
 */
@Controller
//@RequestMapping("sys")
public class SystemController extends BaseController{

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping({"sys/toLogin","/"})
    public String toLogin(){
        return "system/index/login";
    }

    @RequestMapping("sys/index")
    public String index(Model model){
        UserDTO user = this.getCurrentUser();
        String nickName = userService.getNickNameById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("nickname",nickName);
        return "/system/index/index";
    }
}
