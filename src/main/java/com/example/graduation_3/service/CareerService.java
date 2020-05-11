package com.example.graduation_3.service;

import com.example.graduation_3.dto.CareerDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/9 - 18:11
 */
public interface CareerService {

    /**
     * 通过生涯id获得相关用户所有生涯
     * @param id
     * @return
     */
    List<CareerDTO> getCareersById(Long id);
    /**
     * 通过userId获得所有相关的用户信息
     * @param userId
     * @return
     */
    List<CareerDTO> getCareerByUserId(Long userId);

    /**
     * 根据分页信息和传入的key值对多项进行模糊搜索
     * @param page
     * @param limit
     * @param key
     * @return
     */
/*
    List<CareerDTO> search(Integer page,Integer limit,String key);
*/

    /**
     * 获得key值模糊搜索出的生涯总数
     * @param key
     * @return
     */
    /*Integer getCountInSearch(String key);*/

    /**
     * 新增生涯信息
     * @param companyName
     * @param userId
     * @param beginYear
     * @param endYear
     * @param position
     * @param attach
     * @return
     */
    Integer careerAdd(String companyName, Long userId,Date beginYear,
                      Date endYear,String position,String attach);

    /**
     * 如果传入的userId和key为空，则是查询全部生涯信息
     * userId非空则查询该用户所有生涯信息
     * key非空则加个多项模糊搜索
     * @param userId
     * @param page
     * @param limit
     * @param key
     * @return
     */
    List<CareerDTO> getCareerListByUserAndSearch(Long userId,Integer page,Integer limit,String key);

    /**
     * 同上，不过获得的是生涯信息数量
     * @param userId
     * @param key
     * @return
     */
    Integer getCountByUserAndSearch(Long userId,String key);

    /**
     * 通过生涯id删除生涯信息
     * @param id
     */
    void deleteCareerById(Long id);
}
