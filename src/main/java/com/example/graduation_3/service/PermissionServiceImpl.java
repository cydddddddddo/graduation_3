package com.example.graduation_3.service;

import com.example.graduation_3.dto.PermissionDTO;
import com.example.graduation_3.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/6 - 22:23
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> getMenuByType(Integer type,Integer percode) {

        return permissionMapper.getMenuByType(type,percode);
    }


}
