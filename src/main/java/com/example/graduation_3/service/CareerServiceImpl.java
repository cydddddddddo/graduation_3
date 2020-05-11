package com.example.graduation_3.service;

import com.example.graduation_3.dto.CareerDTO;
import com.example.graduation_3.mapper.CareerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/9 - 18:12
 */
@Service
public class CareerServiceImpl implements CareerService{

    @Autowired
    private CareerMapper careerMapper;


    @Override
    public List<CareerDTO> getCareersById(Long id) {
        return careerMapper.getCareersById(id);
    }

    @Override
    public List<CareerDTO> getCareerByUserId(Long userId) {
        return careerMapper.getCareerByUserId(userId);
    }

   /* @Override
    public List<CareerDTO> search(Integer page, Integer limit, String key) {
        return careerMapper.search(page,limit,key);
    }

    @Override
    public Integer getCountInSearch(String key) {
        return careerMapper.getCountInSearch(key);
    }*/

    @Override
    public Integer careerAdd(String companyName, Long userId, Date beginYear, Date endYear, String position, String attach) {
        return careerMapper.careerAdd(companyName,userId,beginYear,endYear,position,attach);
    }

    @Override
    public List<CareerDTO> getCareerListByUserAndSearch(Long userId, Integer page, Integer limit, String key) {
        return careerMapper.getCareerListByUserAndSearch(userId, page, limit, key);
    }

    @Override
    public Integer getCountByUserAndSearch(Long userId, String key) {
        return careerMapper.getCountByUserAndSearch(userId, key);
    }

    @Override
    public void deleteCareerById(Long id) {
        careerMapper.deleteCareerById(id);
    }
}
