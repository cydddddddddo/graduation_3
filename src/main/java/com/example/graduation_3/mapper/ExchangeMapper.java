package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.ExchangeDTO;
import com.example.graduation_3.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 15:30
 */
@Repository
@Mapper
public interface ExchangeMapper {

    List<ExchangeDTO> getExchangeList(@Param("receiveGrade")String receiveGrade,@Param("receiveRole")String receiveRole
                                    ,@Param("receiveCollege")String receiveCollege,@Param("key")String key,@Param("userId")Long userId);

    List<ExchangeDTO> getExchangeListByUserId(@Param("userId")Long userId,@Param("key")String key,@Param("page")Integer page
                            ,@Param("limit")Integer limit,@Param("receiveRole")String receiveRole, @Param("receiveCollege")String receiveCollege,@Param("receiveGrade")String receiveGrade);

    Long getExchangeCount(@Param("userId")Long userId,@Param("key")String key,@Param("receiveRole")String receiveRole,@Param("receiveCollege")String receiveCollege,@Param("receiveGrade")String receiveGrade);

    void deleteExhcangeById(@Param("id")Long id);

    Long getCountByExchangeId(@Param("exchangeId")Long exchangeId);

    ExchangeDTO getExchangeById(@Param("id")Long id);

    Long addExchange(@Param("userId")Long userId, @Param("receiveGrade")String receiveGrade
                    , @Param("receiveRole")String receiveRole, @Param("receiveCollege")String receiveCollege
                    , @Param("title")String title, @Param("message")String message, @Param("sendDate")Timestamp sendDate);
}
