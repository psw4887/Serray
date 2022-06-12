package com.nhnacademy.serrayclient.service;

public interface CommentService {

    String getCommenter(Integer commentNo);

    void registerComment(Integer taskNo, String admin, String content);

    void modifyComment(Integer commentNo, String content);

    void deleteComment(Integer commentNo);
}
