package com.nhnacademy.serrayclient.service;

public interface TagService {

    void registerTag(Integer projectNo, String content, String admin);

    void modifyTag(Integer tagNo, String content);

    void deleteTag(Integer tagNo);

    void addTaskTag(Integer taskNo, Integer tagNo);
}
