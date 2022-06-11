package com.nhnacademy.serraytaskapi.service;

import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;

public interface TagService {

    void projectTagRegister(TagRegisterVO vo);

    void projectTagModify(TagModifyVO vo);

    void projectTagDelete(Integer tagNo);
}
