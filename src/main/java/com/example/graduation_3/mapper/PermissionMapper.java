package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.PermissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/6 - 17:45
 */
@Repository
@Mapper
public interface PermissionMapper {
    /**
     * 根据类型和用户权限获得菜单信息
     * @param type
     * @param percode
     * @return
     */
    List<PermissionDTO> getMenuByType(@Param("type")Integer type,@Param("percode")Integer percode);

}
