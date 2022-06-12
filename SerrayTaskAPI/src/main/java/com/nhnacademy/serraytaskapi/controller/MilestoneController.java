package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TaskMileRegisterVO;
import com.nhnacademy.serraytaskapi.exception.ValidException;
import com.nhnacademy.serraytaskapi.service.MilestoneService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mile")
public class MilestoneController {

    private final MilestoneService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerProjectMile(@RequestBody @Valid MileRegisterVO vo,
                                    BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.projectMileRegister(vo);
    }

    @PutMapping("/modify")
    public void modifyProjectMile(@RequestBody @Valid MileModifyVO vo,
                                  BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.projectMileModify(vo);
    }

    @DeleteMapping("/delete")
    public void deleteProjectMile(@RequestParam("mileNo") Integer mileNo) {

        service.projectMileDelete(mileNo);
    }

    @PostMapping("/task/register")
    public void addTaskMile(@RequestBody @Valid TaskMileRegisterVO vo,
                            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.taskMileRegister(vo);
    }
}
