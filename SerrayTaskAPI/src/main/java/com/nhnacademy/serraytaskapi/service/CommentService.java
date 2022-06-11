package com.nhnacademy.serraytaskapi.service;

import com.nhnacademy.serraytaskapi.data.vo.CommentModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.CommentRegisterVO;

public interface CommentService {

    String getCommenter(Integer commentNo);

    void registerComment(CommentRegisterVO vo);

    void modifyComment(CommentModifyVO vo);

    void deleteComment(Integer commentNo);
}
