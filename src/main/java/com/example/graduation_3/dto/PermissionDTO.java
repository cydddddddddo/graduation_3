package com.example.graduation_3.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Cy
 * @data 2020/5/6 - 17:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *菜单id
     */
    private Integer id;
    /**
     *父菜单id
     */
    private Integer pid;
    /**
     *菜单权限（一级，二级）
     */
    private Integer type;
    /**
     *菜单名称
     */
    private String title;
    /**
     *菜单用户权限
     */
    private Integer percode;
    /**
     * 图标路径或Unicode编码
     */
    private String icon;
    /**
     * 跳转路径
     */
    private String href;
    /**
     * 是否默认打开
     */
    private Integer open;
    /**
     * 是否激活
     */
    private Integer available;
}
