package com.example.graduation_3.service;

import com.example.graduation_3.dto.CommentDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 22:30
 */
public interface CommentService {
    List<CommentDTO> getCommentListByExchangeId(Long exchangeId);

    Integer addComment(Long userId, Long exchangeId, String content, Timestamp sendDate,Long receiveUserId);

    Integer deleteComment(Long id);
}
