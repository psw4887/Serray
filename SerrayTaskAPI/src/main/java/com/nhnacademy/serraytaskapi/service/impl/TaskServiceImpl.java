package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.CommentDataDTO;
import com.nhnacademy.serraytaskapi.data.dto.TaskModifyDataDTO;
import com.nhnacademy.serraytaskapi.data.response.CommentDataResponse;
import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.CommentRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;
    private final CommentRepository cRepository;

    @Override
    public TaskDataResponse getTaskData(Integer taskNo) {

        TaskModifyDataDTO dto = tRepository.findByProjectNoForModifyData(taskNo);
        List<CommentDataDTO> DTOs = cRepository.getCommentListByTaskNo(taskNo);
        List<CommentDataResponse> responses = new ArrayList<>();

        for(CommentDataDTO commentDTO : DTOs) {
            responses.add(
                    new CommentDataResponse(commentDTO.getCommentNo(), commentDTO.getAdmin(), commentDTO.getContent())
            );
        }

        return new TaskDataResponse(
                dto.getTaskNo(), dto.getAdmin(), dto.getTitle(), dto.getContent(), responses);
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
