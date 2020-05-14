package com.example.graduation_3.service;

import com.example.graduation_3.dto.CommentDTO;
import com.example.graduation_3.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/12 - 22:30
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getCommentListByExchangeId(Long exchangeId) {
        return commentMapper.getCommentListByExchangeId(exchangeId);
    }

    @Override
    public Integer addComment(Long userId, Long exchangeId, String content, Timestamp sendDate,Long receiveUserId) {
        return commentMapper.addComment(userId, exchangeId, content, sendDate,receiveUserId);
    }

    @Override
    public Integer deleteComment(Long id) {
        return commentMapper.deleteComment(id);
    }
}
