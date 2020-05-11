package com.example.graduation_3.service;

import com.example.graduation_3.dto.PermissionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/6 - 21:54
 */
public interface PermissionService {

    List<PermissionDTO> getMenuByType(Integer Type,Integer percode);
}
