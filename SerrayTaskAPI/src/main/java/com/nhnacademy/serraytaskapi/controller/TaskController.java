package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @PostMapping("/register")
    public void taskRegister(@RequestBody TaskRegisterVO vo) {

        service.registerTask(vo);
    }
}
