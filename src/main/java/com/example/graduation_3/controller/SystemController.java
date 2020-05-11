package com.example.graduation_3.controller;

import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.MemberServiceImpl;
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

    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 跳转至登录页（有security这里大概是没用了）
     * @return
     */
    @RequestMapping({"sys/toLogin","/"})
    public String toLogin(){
        return "system/index/login";
    }

    /**
     * 登陆成功跳转至首页
     * @param model
     * @return
     */
    @RequestMapping("sys/index")
    public String index(Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        return "system/index/index";
    }

    /**
     * 分页跳转至登录台
     * @param model
     * @return
     */
    @RequestMapping("sys/toDeskManager")
    public String toDeskManager(Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        return "system/index/deskManager";
    }

    /**
     * 跳转至生涯广场
     * @param model
     * @return
     */
    @RequestMapping("career/toCareerSquare")
    public String toCareerSquare(Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        return "career/square/careerList";
    }

    @RequestMapping("career/toCareerDelete")
    public String toCareerDelete(Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        return "career/square/careerDelete";
    }


}
