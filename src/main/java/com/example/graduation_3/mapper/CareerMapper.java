package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.CareerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/9 - 16:03
 */
@Repository
@Mapper
public interface CareerMapper {

    /**
     * 根据生涯id获得相关用户的全部生涯信息
     * @param id
     * @return
     */
    List<CareerDTO> getCareersById(@Param("id")Long id);

    /**
     * 根据用户id获得全部相关生涯信息
     * @param userId
     * @return
     */
    List<CareerDTO> getCareerByUserId(@Param("userId")Long userId);

    /**
     * 获得对多项模糊搜索后的结果
     * +分页设置
     * @param page
     * @param limit
     * @param key
     * @return
     */
/*
    List<CareerDTO> search(@Param("page")Integer page,@Param("limit")Integer limit,@Param("key")String key);
*/

    /**
     * 获得对多项模糊搜索后的结果的总数
     * @param key
     * @return
     */
   /* Integer getCountInSearch(@Param("key")String key);*/

    /**
     * 新增生涯信息
     * （这就是数据库中表和实体参数不一致的代价）
     * @param companyName
     * @param userId
     * @param beginYear
     * @param endYear
     * @param position
     * @param attach
     * @return
     */
    Integer careerAdd(@Param("companyName")String companyName, @Param("userId")Long userId, @Param("beginYear")Date beginYear,
                      @Param("endYear")Date endYear,@Param("position")String position,@Param("attach")String attach);

    /**
     * 获得当前用户的所有生涯信息
     * +key模糊搜索
     * @param userId
     * @param page
     * @param limit
     * @param key
     * @return
     */
    List<CareerDTO> getCareerListByUserAndSearch(@Param("userId")Long userId,@Param("page")Integer page,@Param("limit")Integer limit,@Param("key")String key);

    /**
     * 同上，数量
     * @param userId
     * @param key
     * @return
     */
    Integer getCountByUserAndSearch(@Param("userId")Long userId,@Param("key")String key);

    /**
     * 删除生涯通过生涯id
     * @param id
     */
    void deleteCareerById(@Param("id")Long id);
}
