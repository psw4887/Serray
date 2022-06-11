package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TaskMileRegisterVO;
import com.nhnacademy.serraytaskapi.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mile")
public class MilestoneController {

    private final MilestoneService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerProjectMile(@RequestBody MileRegisterVO vo) {

        service.projectMileRegister(vo);
    }

    @PutMapping("/modify")
    public void modifyProjectMile(@RequestBody MileModifyVO vo) {

        service.projectMileModify(vo);
    }

    @DeleteMapping("/delete")
    public void deleteProjectMile(@RequestParam("mileNo") Integer mileNo) {

        service.projectMileDelete(mileNo);
    }

    @PostMapping("/task/register")
    public void addTaskMile(@RequestBody TaskMileRegisterVO vo) {

        service.taskMileRegister(vo);
    }
}
