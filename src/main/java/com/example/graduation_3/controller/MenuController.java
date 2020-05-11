package com.example.graduation_3.controller;

import com.example.graduation_3.common.Constast;
import com.example.graduation_3.common.DataGridView;
import com.example.graduation_3.common.TreeNode;
import com.example.graduation_3.common.TreeNodeBuilder;
import com.example.graduation_3.dto.PermissionDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.PermissionServiceImpl;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/6 - 23:04
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Autowired
    private PermissionServiceImpl permissionService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获得左侧导航栏数据
     * @return
     */
    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(){
        //获得登录用户信息
        UserDTO user = this.getCurrentUser();
//        UserDTO user = userService.getUserByName("root");
        List<PermissionDTO> list = null;
        //根据权限查询所有菜单
        switch (user.getRole()){
            case "ROLE_stu":
                list=permissionService.getMenuByType(Constast.TYPE_MENU,Constast.PERCODE_STU);
                break;
            case "ROLE_tea":
                list=permissionService.getMenuByType(Constast.TYPE_MENU,Constast.PERCODE_TEA);
                break;
            default:
                list=permissionService.getMenuByType(Constast.TYPE_MENU,Constast.PERCODE_MAN);
                break;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (PermissionDTO p :
                list) {
            Integer id=p.getId();
            Integer pid=p.getPid();
            String title=p.getTitle();
            String icon=p.getIcon();
            String href=p.getHref();
            Boolean spread=(p.getOpen().equals(Constast.OPEN_TRUE))?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }

        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes,1);
        return new DataGridView("cs",list2);
    }
}
