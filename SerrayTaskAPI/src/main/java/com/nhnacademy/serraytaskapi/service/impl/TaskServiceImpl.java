package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.*;
import com.nhnacademy.serraytaskapi.data.response.*;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.entity.TaskMilestone;
import com.nhnacademy.serraytaskapi.entity.TaskTag;
import com.nhnacademy.serraytaskapi.exception.MilestoneNotFoundException;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.*;
import com.nhnacademy.serraytaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;
    private final CommentRepository cRepository;
    private final TagRepository tagRepository;
    private final MilestoneRepository mileRepository;
    private final TaskTagRepository taskTagRepository;
    private final TaskMilestoneRepository taskMilestoneRepository;

    @Override
    public TaskDataResponse getTaskData(Integer taskNo) {

        Task task = tRepository.findById(taskNo).orElseThrow(TaskNotFoundException::new);

        TaskModifyDataDTO dto = tRepository.findByProjectNoForModifyData(taskNo);
        List<CommentDataDTO> commentDTOs = cRepository.getCommentListByTaskNo(taskNo);
        List<ProjectTagDTO> projectTagDTOs = tagRepository.findTagListByProjectNo(task.getProject().getProjectNo());
        List<ProjectMileDTO> projectMileDTOs = mileRepository.findMileListByProjectNo(task.getProject().getProjectNo());
        List<TaskTagDTO> taskTags = taskTagRepository.findTaskTagList(taskNo);


        List<CommentDataResponse> commentResponses = new ArrayList<>();
        List<ProjectTagDataResponse> projectTagResponses = new ArrayList<>();
        List<ProjectMilestoneDataResponse> projectMileResponses = new ArrayList<>();
        List<TagDataResponse> tagResponses = new ArrayList<>();

        for(CommentDataDTO commentDTO : commentDTOs) {
            commentResponses.add(new CommentDataResponse(commentDTO.getCommentNo(), commentDTO.getAdmin(), commentDTO.getContent()));
        }
        for(ProjectTagDTO tagDTO : projectTagDTOs) {
            projectTagResponses.add(new ProjectTagDataResponse(tagDTO.getTagNo(), tagDTO.getContent()));
        }
        for(ProjectMileDTO mileDTO : projectMileDTOs) {
            projectMileResponses.add(new ProjectMilestoneDataResponse(mileDTO.getMileNo(), mileDTO.getContent()));
        }
        for(TaskTagDTO tagDTO : taskTags) {
            tagResponses.add(new TagDataResponse(tagDTO.getTagNo(), tagDTO.getContent()));
        }

        MilestoneDataResponse mileResponse = null;

        if(Boolean.TRUE.equals(taskMilestoneRepository.existsByTask(task))) {
            TaskMileDTO taskMileDTO = taskMilestoneRepository.findTaskMile(taskNo);
            mileResponse = new MilestoneDataResponse(
                    taskMileDTO.getMileNo(), taskMileDTO.getContent(), taskMileDTO.getStart(), taskMileDTO.getEnd());
        }

        return new TaskDataResponse(
                dto.getTaskNo(), dto.getAdmin(), dto.getTitle(), dto.getContent(), commentResponses,
                projectTagResponses, projectMileResponses, tagResponses, mileResponse);
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

        cRepository.deleteByTaskNo(taskNo);
        taskTagRepository.deleteByTaskNo(taskNo);
        taskMilestoneRepository.deleteByTaskNo(taskNo);
        tRepository.deleteById(taskNo);
    }
}
