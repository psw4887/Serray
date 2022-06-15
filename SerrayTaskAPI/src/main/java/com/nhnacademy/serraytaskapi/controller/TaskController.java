package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.exception.ValidException;
import com.nhnacademy.serraytaskapi.service.TaskService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/tasks")
public class TaskController {

    private final TaskService service;

    @GetMapping("/{taskNo}")
    public TaskDataResponse getDataByTask(@PathVariable("taskNo") Integer taskNo) {

        return service.getTaskData(taskNo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void taskRegister(@RequestBody @Valid TaskRegisterVO vo,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.registerTask(vo);
    }

    @PutMapping("/modify")
    public void taskModify(@RequestBody @Valid TaskModifyVo vo,
                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.modifyTask(vo);
    }

    @DeleteMapping("/{taskNo}/delete")
    public void taskDelete(@PathVariable("taskNo") Integer taskNo) {

        service.deleteTask(taskNo);
    }
}
