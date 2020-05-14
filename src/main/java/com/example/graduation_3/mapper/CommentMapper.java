package com.example.graduation_3.mapper;

import com.example.graduation_3.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 22:29
 */
@Repository
@Mapper
public interface CommentMapper {

    List<CommentDTO> getCommentListByExchangeId(@Param("exchangeId")Long exchangeId);

    Integer addComment(@Param("userId")Long userId, @Param("exchangeId")Long exchangeId
            , @Param("content")String content, @Param("sendDate")Timestamp sendDate,@Param("receiveUserId")Long receiveUserId);

    Integer deleteComment(@Param("id")Long id);
}
