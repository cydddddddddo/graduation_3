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
     * 学生只能获得他所在年级、所在学院、发给学生的帖子
     * 而老师可以获得所在年级、所在学院，发给学生、老师、管理的帖子
     * 而管理可以获得所有的帖子
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toExchangeList(String key,Model model){
        UserDTO tempU = null;
        MemberDTO tempM = null;
        List<ExchangeDTO> list = null;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if ("ROLE_stu".equals(user.getRole())){
            list = exchangeService.getExchangeList(member.getGrade(),user.getRole()
                    ,member.getCollege(),null,null);
        }else if ("ROLE_tea".equals(user.getRole())){
            list = exchangeService.getExchangeList(member.getGrade(),null
                    ,member.getCollege(),null,null);
        }else {
            list = exchangeService.getExchangeList(null,null
                    ,null,null,null);
        }


        for (ExchangeDTO e :
                list) {
            tempU = userService.getUserByid(e.getUserId());
            tempM = memberService.getMemberById(e.getUserId());
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setSendCollege(tempM.getCollege());
            e.setSendGrade(tempM.getGrade());
            e.setSendRole(tempU.getRole());
            //这里是代价
            e.setMember(tempM);
        }
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        model.addAttribute("exchangelist",list);
        return "exchange/exchangeList";
    }

    /**
     * 此处唯一的区别是学生老师可以获得不同年级的帖子
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/friendExchange")
    public String friendsExchange(String key,Model model){
        UserDTO tempU = null;
        MemberDTO tempM = null;
        List<ExchangeDTO> list = null;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if ("ROLE_stu".equals(user.getRole())){
            list = exchangeService.getExchangeList(null,user.getRole()
                    ,member.getCollege(),null,null);
        }else if ("ROLE_tea".equals(user.getRole())){
            list = exchangeService.getExchangeList(null,null
                    ,member.getCollege(),null,null);
        }else {
            list = exchangeService.getExchangeList(null,null
                    ,null,null,null);
        }

        for (ExchangeDTO e :
                list) {
            tempU = userService.getUserByid(e.getUserId());
            tempM = memberService.getMemberById(e.getUserId());
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setSendCollege(tempM.getCollege());
            e.setSendGrade(tempM.getGrade());
            e.setSendRole(tempU.getRole());
            //这里是代价
            e.setMember(tempM);
        }
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        model.addAttribute("exchangelist",list);
        return "exchange/friendsExchange";
    }

    /**
     * 因为8知道如何利用数据重新加载页面
     * 所以干脆直接重新进入这个页面
     * 即搜索后返回新的内容
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/search/{key}")
    public String search(@PathVariable String key,Model model){
        UserDTO tempU = null;
        MemberDTO tempM = null;
        List<ExchangeDTO> list = null;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if ("ROLE_stu".equals(user.getRole())){
            list = exchangeService.getExchangeList(member.getGrade(),user.getRole()
                    ,member.getCollege(),key,null);
        }else if ("ROLE_tea".equals(user.getRole())){
            list = exchangeService.getExchangeList(member.getGrade(),null
                    ,member.getCollege(),key,null);
        }else {
            list = exchangeService.getExchangeList(null,null
                    ,null,key,null);
        }

        for (ExchangeDTO e :
                list) {
            tempU = userService.getUserByid(e.getUserId());
            tempM = memberService.getMemberById(e.getUserId());
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setSendCollege(tempM.getCollege());
            e.setSendGrade(tempM.getGrade());
            e.setSendRole(tempU.getRole());
            //代价
            e.setMember(tempM);
        }
        model.addAttribute("user",user);
        model.addAttribute("member",member);
        model.addAttribute("exchangelist",list);
        return "exchange/exchangeList";
    }


    /**
     * 新增帖子
     * @param exchange
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(ExchangeDTO exchange,Model model){
        Long num = 0L;
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
             num = exchangeService.addExchange(exchange);
                  if (num!=0){
                      return "exchange/exchangeDelete";
                  }else {
                      return "exchange/exchangeAdd";
                  }
        }else {
            model.addAttribute("user",user);
            model.addAttribute("member",member);
            return "exchange/exchangeAdd";
        }
    }

    /**
     * 进入帖子详情
     * @param exchangeId
     * @param model
     * @return
     */
    @RequestMapping("/particulars")
    public String toParticulars(@RequestParam Long exchangeId, Model model){

        ExchangeDTO exchange = exchangeService.getExchangeById(exchangeId);
        UserDTO tempU = userService.getUserByid(exchange.getUserId());
        MemberDTO tempM = memberService.getMemberById(exchange.getUserId());
        exchange.setCommentCount(exchangeService.getCountByExchangeId(exchange.getId()));
        exchange.setSendCollege(tempM.getCollege());
        exchange.setSendGrade(tempM.getGrade());
        exchange.setSendRole(tempU.getRole());

        //代价
        exchange.setMember(tempM);

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

    /**
     * 获得帖子中的评论
     * @param exchangeId
     * @param model
     * @return
     */
    @RequestMapping("/particulars/{exchangeId}")
    public String toParticulars_2(@PathVariable Long exchangeId, Model model){

        ExchangeDTO exchange = exchangeService.getExchangeById(exchangeId);
        UserDTO tempU = userService.getUserByid(exchange.getUserId());
        MemberDTO tempM = memberService.getMemberById(exchange.getUserId());
        exchange.setCommentCount(exchangeService.getCountByExchangeId(exchange.getId()));
        exchange.setSendCollege(tempM.getCollege());
        exchange.setSendGrade(tempM.getGrade());
        exchange.setSendRole(tempU.getRole());

        //代价
        exchange.setMember(tempM);

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

    /**
     * 新增评论
     * @param content
     * @param userId
     * @param exchangeId
     * @param model
     * @return
     */
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

    /**
     * 删除评论
     * @param exchangeId
     * @param commentId
     * @param model
     * @return
     */
    @RequestMapping("/particulars/delete")
    public String deleteParticulars(Long exchangeId,Long commentId,Model model){
        int num = commentService.deleteComment(commentId);
        if (num==1){
            return toParticulars(exchangeId,model);
        }else {
            return "404";
        }
    }

    /**
     * 管理帖子列表
     * @param page
     * @param limit
     * @param key
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete/list")
    public DataGridView getExchangeByUserId(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit
            ,String key,Model model){
        List<ExchangeDTO> list = null;
        Long count = 0L;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if (!"ROLE_man".equals(user.getRole())){
            list = exchangeService.getExchangeListByUserId(user.getId(),key,page,limit,null,null,null);
            count = exchangeService.getExchangeCount(user.getId(),key,null,null,null);
        }else {
            list = exchangeService.getExchangeListByUserId(null,key,page,limit,null,null,null);
            count = exchangeService.getExchangeCount(null,key,null,null,null);

        }

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
        return new DataGridView(count,list);
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
        List<ExchangeDTO> list = null;
        Long count = 0L;
        UserDTO user = this.getCurrentUser();
        MemberDTO member = memberService.getMemberById(user.getId());
        if ("ROLE_stu".equals(user.getRole())){
            list = exchangeService.getExchangeListByUserId(user.getId(),key,page,limit,"ROLE_tea",null,null);
            count = exchangeService.getExchangeCount(user.getId(),key,"ROLE_tea",null,null);
        }else if("ROLE_tea".equals(user.getRole())){
            list = exchangeService.getExchangeListByUserId(null,key,page,limit,"ROLE_tea",member.getCollege(),member.getGrade());
            count = exchangeService.getExchangeCount(null,key,"ROLE_tea",member.getCollege(),member.getGrade());

        }else {
            list = exchangeService.getExchangeListByUserId(null,key,page,limit,"ROLE_tea",null,null);
            count = exchangeService.getExchangeCount(null,key,"ROLE_tea",null,null);
        }
        for (ExchangeDTO e :
                list) {
            e.setCommentCount(exchangeService.getCountByExchangeId(e.getId()));
            e.setReceiveRole("教师");
            //解决时间格式问题
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINESE);
            e.setChangeDate( sdf.format(e.getSendDate()));
        }
        return new DataGridView(count,list);
    }
}
