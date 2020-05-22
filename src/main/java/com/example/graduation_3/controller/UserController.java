package com.example.graduation_3.controller;

import com.example.graduation_3.common.Constast;
import com.example.graduation_3.common.DataGridView;
import com.example.graduation_3.common.ResultObj;
import com.example.graduation_3.common.UploadUtil;
import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.MemberServiceImpl;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Cy
 * @data 2020/4/30 - 16:47
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 代码端新增用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("user/addUser")
    @ResponseBody
    public int addUser(@RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    /**
     * 修改用户信息
     * @param userId
     * @param email
     * @param synopsis
     * @param nickName
     * @param grade
     * @param college
     * @param image
     * @param model
     * @return
     */
    @RequestMapping("member/amend")
    @ResponseBody
    public ResultObj amendMember(Long userId, String email, String synopsis, String nickName
            , String grade, String college, String image, Model model) {
        int num = memberService.amendMember(email, synopsis, userId, nickName, grade, college, image);
        if (num == 1) {
            return ResultObj.AMEND_SUCCESS;
        } else {
            return ResultObj.AMEND_FAILED;
        }
    }

    /**
     * 上传头像
     * @param file
     * @param model
     * @return
     */
    @RequestMapping("upload/image")
    @ResponseBody
    public DataGridView demo(MultipartFile file, Model model) {
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        try {
            String image = UploadUtil.uploadFile(file, Constast.IMAGE_SET_PATH, user.getId());

            System.out.println(image);
            ResultObj resultObj = this.amendMember(user.getId(), member.getEmail()
                        , member.getSynopsis(), null, null
                    , null, image, model);
            if (resultObj.equals(ResultObj.AMEND_SUCCESS)) {
                return new DataGridView(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataGridView("err");
    }

    /**
     * 修改密码，返回相应信息
     * @param oldPwd
     * @param newPwd
     * @param realPwd
     * @param model
     * @return
     */
    @RequestMapping("user/changePwd/change")
    @ResponseBody
    public ResultObj changePwd(String oldPwd, String newPwd, String realPwd, Model model) {
        //这里堪称薛定谔的bug，好吧其实不是后端的问题，是前端。
        int num = 0;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        if (oldPwd.equals(newPwd)) {
            //新旧密码是否一致
            return ResultObj.CHANGE_NEWPWD_FAILED;
        }else if (!realPwd.equals(newPwd)){
            //确认密码不一致
            return ResultObj.CHANGE_REALPWD_FAILED;
        } else if (passwordEncoder.matches(oldPwd, user.getPassword())) {
            //验证旧密码，对则修改，错则返回错误信息
            user.setPassword(newPwd);
            //对密码进行修改
            num = userService.changeUser(user);
            return ResultObj.CHANGE_SUCCESS;
        } else {
            //旧密码错误
            return ResultObj.CHANGE_OLDPWD_FAILED;
        }
    }

    /**
     * 用于验证解锁锁屏密码
     * @param password
     * @return
     */
    @RequestMapping("user/verify")
    @ResponseBody
    public ResultObj verify(String password){
        UserDTO user = this.getCurrentUser();
        if (passwordEncoder.matches(password, user.getPassword())){
            return ResultObj.VERIFY_SUCCESS;
        }else {
            return ResultObj.VERIFY_FAILED;
        }
    }

    @RequestMapping("user/list")
    @ResponseBody
    public DataGridView userList(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key,Model model){
        List<MemberDTO> list = null;
        UserDTO tempU = null;
        Long count = 0L;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        list = userService.getUserList(page, limit, key);
        count = userService.getCount(key);

        for (MemberDTO m :
                list) {
            tempU = userService.getUserByid(m.getUserId());
            m.setAccount(tempU.getName());
            m.setRole(tempU.getRole());
        }

        return new DataGridView(count,list);
    }

    @RequestMapping("user/amend")
    @ResponseBody
    public ResultObj amend(String field,String value,Long id,Model model){
        int num = 0;
        MemberDTO tempM = null;
        UserDTO tempU = null;
        Long userId = userService.getUserIdByMemberId(id);
        if ("nickName".equals(field)){
           tempM = new MemberDTO(userId,value,null,null,null,null);
           num = userService.amendMember(tempM);
        }else if ("grade".equals(field)){
            tempM = new MemberDTO(userId,null,value,null,null,null);
            num = userService.amendMember(tempM);
        }else if ("college".equals(field)){
            tempM = new MemberDTO(userId,null,null,value,null,null);
            num = userService.amendMember(tempM);
        }else if ("synopsis".equals(field)){
            tempM = new MemberDTO(userId,null,null,null,value,null);
            num = userService.amendMember(tempM);
        }else if ("email".equals(field)){
            tempM = new MemberDTO(userId,null,null,null,null,value);
            num = userService.amendMember(tempM);
        }else if ("name".equals(field)){
            tempU = new UserDTO(userId,value,null,null);
            num = userService.amendUser(tempU);
        }else if ("role".equals(field)){
            if ("ROLE_".equals(value.substring(0,5))){
                tempU = new UserDTO(userId,null,null,value);
                num = userService.amendUser(tempU);
            }else {
                if ("stu".equals(value)||"tea".equals(value)||"man".equals(value)){
                    value = "ROLE_"+value;
                    num = userService.amendUser(tempU);
                }else {
                    //权限格式错误
                    return ResultObj.AMEND_ROLE_FAILED;
                }
            }
        }
        if (num!=0){
            return ResultObj.AMEND_USER_SUCCESS;
        }else {
            return ResultObj.AMEND_USER_FAILED;
        }
    }


    @RequestMapping("user/add")
    public String userAdd(String name,String password,String role,String grade,
                          String college,String nickName,String email,String synopsis,Model model) throws Exception {
        int num = 0;
        List<UserDTO> list = null;
        MemberDTO tempM = null;
        UserDTO tempU = null;

        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        model.addAttribute("user",user);
        model.addAttribute("member",member);

        if (name!=null||password!=null||role!=null){
            tempU = new UserDTO(null,name,password,role);
            num = userService.addUser(tempU);
            if (num!=0){
                list = userService.getUserId(tempU);
                if (list.size()!=1){
                    throw new Exception("请找编程人员");
                }else {
                    tempM = new MemberDTO(list.get(0).getId(),nickName,grade,college,synopsis,email);
                    num = userService.addMember(tempM);
                    if (num!=0){
                        return "user/userManage";
                    }
                }
            }
        }

        return "user/userAdd";
    }


    @RequestMapping("user/delete")
    @ResponseBody
    public ResultObj deleteExchange(String deletesId,Model model){
        if (deletesId!=null){
            Long id = null;
            Long userId = null;
            String[] arrs = deletesId.split(",");
            Long[] array = new Long[arrs.length];
            for (int i = 0; i < arrs.length; i++) {
                array[i] = Long.parseLong(arrs[i]);
            }
            for (int i = 0; i < array.length; i++) {
                id = array[i];
                userId = userService.getUserIdByMemberId(id);
                userService.deleteMember(id);
                userService.deleteUser(userId);
            }
            return ResultObj.DELETE_SUCCESS;
        }
        return ResultObj.DELETE_FAILED;
    }
}
