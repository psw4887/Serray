package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
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

    @Transactional
    @Override
    public void registerTask(TaskRegisterVO vo) {

        Project project = pRepository.findById(vo.getProjectNo()).orElseThrow(ProjectNotFoundException::new);

        Task task = new Task(project, vo.getId(), vo.getTitle(), vo.getContent());

        tRepository.save(task);
    }
}
