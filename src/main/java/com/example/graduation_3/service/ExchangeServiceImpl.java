package com.example.graduation_3.service;

import com.example.graduation_3.dto.ExchangeDTO;
import com.example.graduation_3.mapper.ExchangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 16:29
 */
@Service
public class ExchangeServiceImpl implements ExchangeService{

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Override
    public List<ExchangeDTO> getExchangeList(String receiveGrade, String receiveRole, String receiveCollege, String key,Long userId) {
        return exchangeMapper.getExchangeList(receiveGrade, receiveRole, receiveCollege, key,userId);
    }

    @Override
    public Long getCountByExchangeId(Long exchangeId) {
        return exchangeMapper.getCountByExchangeId(exchangeId);
    }

    @Override
    public ExchangeDTO getExchangeById(Long id) {
        return exchangeMapper.getExchangeById(id);
    }

    @Override
    public Long addExchange(ExchangeDTO exchange) {
        return exchangeMapper.addExchange(exchange.getUserId(),exchange.getReceiveGrade(),exchange.getReceiveRole()
                                        ,exchange.getReceiveCollege(),exchange.getTitle(),exchange.getMessage(),exchange.getSendDate());
    }

    @Override
    public List<ExchangeDTO> getExchangeListByUserId(Long userId, String key, Integer page, Integer limit,String receiveRole) {
        return exchangeMapper.getExchangeListByUserId(userId, key, page, limit,receiveRole);
    }

    @Override
    public void deleteExhcangeById(Long id) {
        exchangeMapper.deleteExhcangeById(id);
    }


}
