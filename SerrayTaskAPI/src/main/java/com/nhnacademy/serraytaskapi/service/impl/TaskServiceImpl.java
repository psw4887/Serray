package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.TaskModifyDataDTO;
import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;

    @Override
    public TaskDataResponse getTaskData(Integer taskNo) {

        TaskModifyDataDTO dto = tRepository.findByProjectNoForModifyData(taskNo);

        return new TaskDataResponse(
                dto.getTaskNo(), dto.getAdmin(), dto.getTitle(), dto.getContent());
    }

    @Transactional
    @Override
    public void registerTask(TaskRegisterVO vo) {

        Project project = pRepository.findById(vo.getProjectNo()).orElseThrow(ProjectNotFoundException::new);

        Task task = new Task(project, vo.getId(), vo.getTitle(), vo.getContent());

        tRepository.save(task);
    }

    @Transactional
    @Override
    public void modifyTask(TaskModifyVo vo) {

        Task task = tRepository.findById(vo.getTaskNo()).orElseThrow(TaskNotFoundException::new);

        task.setTitle(vo.getTitle());
        task.setContent(vo.getContent());

        tRepository.save(task);
    }

    @Transactional
    @Override
    public void deleteTask(Integer taskNo) {

        tRepository.deleteById(taskNo);
    }
}
