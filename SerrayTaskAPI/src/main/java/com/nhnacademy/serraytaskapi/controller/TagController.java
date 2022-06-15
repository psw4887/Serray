package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;
import com.nhnacademy.serraytaskapi.exception.ValidException;
import com.nhnacademy.serraytaskapi.service.TagService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/tags")
public class TagController {

    private final TagService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerProjectTag(@RequestBody @Valid TagRegisterVO vo,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.projectTagRegister(vo);
    }

    @PutMapping("/modify")
    public void modifyProjectTag(@RequestBody @Valid TagModifyVO vo,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.projectTagModify(vo);
    }

    @DeleteMapping("/{tagNo}/delete")
    public void deleteProjectTag(@PathVariable("tagNo") Integer tagNo) {

        service.projectTagDelete(tagNo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{tagNo}/tasks/{taskNo}/register")
    public void addTaskTag(@PathVariable("taskNo") Integer taskNo,
                        @PathVariable("tagNo") Integer tagNo) {

        service.taskTagRegister(taskNo, tagNo);
    }
}
