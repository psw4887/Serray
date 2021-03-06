package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Tag;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.entity.TaskTag;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TagNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TagRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.repository.TaskTagRepository;
import com.nhnacademy.serraytaskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    @Transactional
    @Override
    public void projectTagRegister(TagRegisterVO vo) {

        Project project = pRepository.findById(vo.getProjectNo()).orElseThrow(ProjectNotFoundException::new);
        Tag tag = new Tag(project, vo.getContent(), vo.getAdmin());

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void projectTagModify(TagModifyVO vo) {

        Tag tag = tagRepository.findById(vo.getTagNo()).orElseThrow(TagNotFoundException::new);

        tag.setContent(vo.getContent());

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void projectTagDelete(Integer tagNo) {

        taskTagRepository.deleteByTagNo(tagNo);

        tagRepository.deleteById(tagNo);
    }

    @Transactional
    @Override
    public void taskTagRegister(Integer taskNo, Integer tagNo) {

        TaskTag.TaskTagPK pk = new TaskTag.TaskTagPK(taskNo, tagNo);

        if (taskTagRepository.findById(pk).isEmpty()) {
            Task task = tRepository.findById(taskNo).orElseThrow(TaskNotFoundException::new);
            Tag tag = tagRepository.findById(tagNo).orElseThrow(TagNotFoundException::new);

            taskTagRepository.save(new TaskTag(pk, tag, task));
        }
    }
}
