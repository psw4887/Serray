package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @GetMapping
    public TaskDataResponse getDataByTask(@RequestParam("taskNo") Integer taskNo) {

        return service.getTaskData(taskNo);
    }

    @PostMapping("/register")
    public void taskRegister(@RequestBody TaskRegisterVO vo) {

        service.registerTask(vo);
    }

    @PutMapping("/modify")
    public void taskModify(@RequestBody TaskModifyVo vo) {

        service.modifyTask(vo);
    }

    @DeleteMapping("/delete/{taskNo}")
    public void taskDelete(@PathVariable("taskNo") Integer taskNo) {

        service.deleteTask(taskNo);
    }
}
