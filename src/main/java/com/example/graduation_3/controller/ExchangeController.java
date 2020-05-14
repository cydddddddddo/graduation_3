package com.example.graduation_3.controller;

import com.example.graduation_3.common.DataGridView;
import com.example.graduation_3.common.ResultObj;
import com.example.graduation_3.dto.CommentDTO;
import com.example.graduation_3.dto.ExchangeDTO;
import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.CommentServiceImpl;
import com.example.graduation_3.service.ExchangeServiceImpl;
import com.example.graduation_3.service.MemberServiceImpl;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Cy
 * @data 2020/5/12 - 16:29
 */
@Controller
@RequestMapping("exchange")
public class ExchangeController extends BaseController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private ExchangeServiceImpl exchangeService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 获得帖子列表
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toExchangeList(String key,Model model){
        UserDTO tempU = null;
        MemberDTO tempM = null;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        List<ExchangeDTO> list = exchangeService.getExchangeList(member.getGrade(),user.getRole()
                                                        ,member.getCollege(),null,null);
        for (ExchangeDTO e :
                list) {
            tempU = userService.getUserByid(e.getUserId());
            tempM = memberService.getMemberById(e.getUserId());
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setSendCollege(tempM.getCollege());
            e.setSendGrade(tempM.getGrade());
            e.setSendRole(tempU.getRole());
        }
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        model.addAttribute("exchangelist",list);
        return "exchange/exchangeList";
    }

    /**
     * 因为8知道如何利用数据重新加载页面
     * 所以干脆直接重新进入这个页面
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/search/{key}")
    public String search(@PathVariable String key,Model model){
        UserDTO tempU = null;
        MemberDTO tempM = null;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        List<ExchangeDTO> list = exchangeService.getExchangeList(member.getGrade(),user.getRole()
                ,member.getCollege(),key,null);
        for (ExchangeDTO e :
                list) {
            tempU = userService.getUserByid(e.getUserId());
            tempM = memberService.getMemberById(e.getUserId());
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setSendCollege(tempM.getCollege());
            e.setSendGrade(tempM.getGrade());
            e.setSendRole(tempU.getRole());
        }
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        model.addAttribute("exchangelist",list);
        return "exchange/exchangeList";
    }


    @RequestMapping("/add")
    public String add(ExchangeDTO exchange,Model model){
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if (exchange.getTitle()!=null){
            Timestamp sendDate = new Timestamp(System.currentTimeMillis());
            exchange.setSendDate(sendDate);
            exchange.setUserId(user.getId());
            if (!exchange.getReceiveGrade().endsWith("级")){
                exchange.setReceiveGrade(exchange.getReceiveGrade()+"级");
            }
            //写入
             Long num = exchangeService.addExchange(exchange);
            return this.toExchangeList(null,model);
        }else {
            model.addAttribute("user",user);
            model.addAttribute("member",member);
            return "exchange/exchangeAdd";
        }
    }

    @RequestMapping("/particulars")
    public String toParticulars(@RequestParam Long exchangeId, Model model){

        ExchangeDTO exchange = exchangeService.getExchangeById(exchangeId);
        UserDTO tempU = userService.getUserByid(exchange.getUserId());
        MemberDTO tempM = memberService.getMemberById(exchange.getUserId());
        exchange.setCommentCount(exchangeService.getCountByExchangeId(exchange.getId()));
        exchange.setSendCollege(tempM.getCollege());
        exchange.setSendGrade(tempM.getGrade());
        exchange.setSendRole(tempU.getRole());

        List<CommentDTO> list = commentService.getCommentListByExchangeId(exchangeId);
        for (CommentDTO c :
                list) {
            c.setMember(memberService.getMemberById(c.getUserId()));
            c.setUser(userService.getUserByid(c.getUserId()));
        }

        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        model.addAttribute("commentList",list);
        model.addAttribute("sendMember",tempM);
        model.addAttribute("exchange",exchange);
        model.addAttribute("user",user);
        model.addAttribute("member",member);

        return "exchange/particulars";

    }

    @RequestMapping("/particulars/{exchangeId}")
    public String toParticulars_2(@PathVariable Long exchangeId, Model model){

        ExchangeDTO exchange = exchangeService.getExchangeById(exchangeId);
        UserDTO tempU = userService.getUserByid(exchange.getUserId());
        MemberDTO tempM = memberService.getMemberById(exchange.getUserId());
        exchange.setCommentCount(exchangeService.getCountByExchangeId(exchange.getId()));
        exchange.setSendCollege(tempM.getCollege());
        exchange.setSendGrade(tempM.getGrade());
        exchange.setSendRole(tempU.getRole());

        List<CommentDTO> list = commentService.getCommentListByExchangeId(exchangeId);
        for (CommentDTO c :
                list) {
            c.setMember(memberService.getMemberById(c.getUserId()));
            c.setUser(userService.getUserByid(c.getUserId()));
        }

        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        model.addAttribute("commentList",list);
        model.addAttribute("sendMember",tempM);
        model.addAttribute("exchange",exchange);
        model.addAttribute("user",user);
        model.addAttribute("member",member);

        return "exchange/particulars";

    }


    @RequestMapping("/particulars/add")
    public String addParticulars(String content,Long userId,Long exchangeId,Model model){
        Timestamp sendDate = new Timestamp(System.currentTimeMillis());
        int num = commentService.addComment(userId,exchangeId,content,sendDate,null);

        if (num==1){
            return toParticulars(exchangeId,model);
        }else {
            return "404";
        }
    }

    @RequestMapping("/particulars/delete")
    public String deleteParticulars(Long exchangeId,Long commentId,Model model){
        int num = commentService.deleteComment(commentId);
        if (num==1){
            return toParticulars(exchangeId,model);
        }else {
            return "404";
        }
    }

    @ResponseBody
    @RequestMapping("/delete/list")
    public DataGridView getExchangeByUserId(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key,Model model){

        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        List<ExchangeDTO> list = exchangeService.getExchangeListByUserId(user.getId(),key,page,limit,null);
        for (ExchangeDTO e :
                list) {
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            if (e.getReceiveRole().equals("ROLE_stu")){
                e.setReceiveRole("学生");
            }else if (e.getReceiveRole().equals("ROLE_tea")){
                e.setReceiveRole("教师");
            }else {
                e.setReceiveRole("管理");
            }
            //解决时间格式问题
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINESE);
            e.setChangeDate( sdf.format(e.getSendDate()));
        }
        return new DataGridView(list);
    }

    @RequestMapping("/delete/delete")
    @ResponseBody
    public ResultObj deleteExchange(String deletesId,Model model){
        if (deletesId!=null){
            String[] arrs = deletesId.split(",");
            Long[] array = new Long[arrs.length];
            for (int i = 0; i < arrs.length; i++) {
                array[i] = Long.parseLong(arrs[i]);
            }
            for (int i = 0; i < array.length; i++) {
                exchangeService.deleteExhcangeById(array[i]);
            }
            return ResultObj.DELETE_SUCCESS;
        }
        return ResultObj.DELETE_FAILED;
    }

    @ResponseBody
    @RequestMapping("/college/list")
    public DataGridView getExchangeByUserIdAndReceiveCollege(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key,Model model){

        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());

        List<ExchangeDTO> list = exchangeService.getExchangeListByUserId(user.getId(),key,page,limit,"ROLE_tea");
        for (ExchangeDTO e :
                list) {
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setReceiveRole("教师");
            //解决时间格式问题
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINESE);
            e.setChangeDate( sdf.format(e.getSendDate()));
        }
        return new DataGridView(list);
    }



}
