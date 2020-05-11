package com.example.graduation_3.common;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 常量接口
 * @author Cy
 * @data 2020/4/30 - 17:18
 */
public interface Constast {

    /**
     * 状态码
     *
     */
    Integer OK=200;
    Integer ERROR=-1;


    Integer TYPE_MENU = 1;
    Integer TYPE_PERMISSION = 2;

    Integer PERCODE_STU = 1;
    Integer PERCODE_TEA = 2;
    Integer PERCODE_MAN = 3;

    Integer OPEN_TRUE = 1;
    Integer OPEN_FALSE = 0;
}
