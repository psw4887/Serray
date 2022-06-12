package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.vo.CommentModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.CommentRegisterVO;
import com.nhnacademy.serraytaskapi.exception.ValidException;
import com.nhnacademy.serraytaskapi.service.CommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;

    @GetMapping("/get")
    String commenterGet(@RequestParam("commentNo") Integer commentNo) {

        return service.getCommenter(commentNo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    void commentRegister(@RequestBody @Valid CommentRegisterVO vo,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.registerComment(vo);
    }

    @PutMapping("/modify")
    void commentModify(@RequestBody @Valid CommentModifyVO vo,
                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.modifyComment(vo);
    }

    @DeleteMapping("/delete")
    void commentDelete(@RequestParam("commentNo") Integer commentNo) {

        service.deleteComment(commentNo);
    }
}
