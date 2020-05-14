package com.example.graduation_3.service;

import com.example.graduation_3.dto.ExchangeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 16:28
 */
public interface ExchangeService {
    List<ExchangeDTO> getExchangeList(String receiveGrade,String receiveRole
                                        ,String receiveCollege,String key,Long userId);

    Long getCountByExchangeId(Long exchangeId);

    ExchangeDTO getExchangeById(Long id);

    Long addExchange(ExchangeDTO exchange);

    List<ExchangeDTO> getExchangeListByUserId(Long userId,String key,Integer page,Integer limit,String receiveRole);

    void deleteExhcangeById(Long id);

}
