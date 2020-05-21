package com.example.graduation_3.controller;

import com.example.graduation_3.common.DataGridView;
import com.example.graduation_3.common.ResultObj;
import com.example.graduation_3.dto.CareerDTO;
import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.CareerServiceImpl;
import com.example.graduation_3.service.MemberServiceImpl;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/9 - 18:24
 */
@Controller
@RequestMapping("/career")
public class CareerController extends BaseController {

    @Autowired
    private CareerServiceImpl careerService;


    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 获得生涯列表
     * 如果传入key非空则进行多项模糊查询
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public DataGridView getCareerList(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key){
        List<CareerDTO> list = careerService.getCareerListByUserAndSearch(null,page, limit, key);
        Long count = Long.valueOf(careerService.getCountByUserAndSearch(null,key));
       return new DataGridView(count,list);
    }

    /**
     * 获得可以删除的生涯列表
     * 其中学生和老师只能删除自己的生涯信息
     * 而管理员可以删除所有的生涯信息
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete/list")
    public DataGridView getCareerListByUser(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key){
        UserDTO user = this.getCurrentUser();
        List<CareerDTO> list = null;
        Long count = null;
        if ("ROLE_stu".equals(user.getRole())||"ROLE_tea".equals(user.getRole())){
            list =  careerService.getCareerListByUserAndSearch(user.getId(), page, limit, key);
            count = Long.valueOf(careerService.getCountByUserAndSearch(user.getId(),key));
        }else{
            list =  careerService.getCareerListByUserAndSearch(null, page, limit, key);
            count = Long.valueOf(careerService.getCountByUserAndSearch(null,key));
        }
        return new DataGridView(count,list);
    }

    /**
     * 执行删除，多选单选皆可
     * @param deletesId
     * @param model
     * @return
     */
    @RequestMapping("/delete/delete")
    @ResponseBody
    public ResultObj deleteCareerList(String deletesId,Model model){
        if (deletesId!=null){
            String[] arrs = deletesId.split(",");
            Long[] array = new Long[arrs.length];
            for (int i = 0; i < arrs.length; i++) {
                array[i] = Long.parseLong(arrs[i]);
            }
            for (int i = 0; i < array.length; i++) {
                careerService.deleteCareerById(array[i]);
            }
            return ResultObj.DELETE_SUCCESS;
        }
        return ResultObj.DELETE_FAILED;
    }

    /**
     * 获得对应用户的全部生涯信息
     * 若传入的生涯id为0，则根据用户id进行查询
     * @param id 生涯id信息
     * @param model
     * @return
     */
    @RequestMapping("/timeline/{id}")
    public String getCareersById(@PathVariable Long id, Model model){
        List<CareerDTO> list = null;
        UserDTO user = this.getCurrentUser();
        if (id==0){
            list = careerService.getCareerByUserId(user.getId());
        }else {
            list = careerService.getCareersById(id);
        }
        MemberDTO member = memberService.getMemberById(user.getId());
        model.addAttribute("member",member);
        model.addAttribute("careerList",list);
        return "career/square/timeline";
    }

    /**
     *新增生涯信息
     * （这里就体现了数据库和实体类不一致是多么愚蠢了
     * 方便了一部分查询，恶心了另一部分）
     * @param career
     * @param model
     * @return
     */
    @RequestMapping("/toCareerSquare/add")
    public String toCareerSquareAdd(CareerDTO career, Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        List<CareerDTO> list = null;

        model.addAttribute("user",user);
        model.addAttribute("member",member);

        if (null!=career.getCompanyName()&&null!=career.getBeginYear()&&null!=career.getPosition()){
            int num = careerService.careerAdd(career.getCompanyName(),user.getId(),career.getBeginYear()
                    ,career.getEndYear(),career.getPosition(),career.getAttach());

            list = careerService.getCareerByUserId(user.getId());
            model.addAttribute("careerList",list);
            return "career/square/timeline";
        }
        return "career/square/careerAdd";
    }
}
