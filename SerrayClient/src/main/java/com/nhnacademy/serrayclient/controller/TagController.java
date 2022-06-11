package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @PostMapping("/register")
    public String tagRegister(@RequestParam("projectNo") Integer projectNo,
                              @RequestParam("content") String content,
                              Principal principal) {

        tagService.registerTag(projectNo, content, principal.getName());

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/modify")
    public String readyTagModify(@RequestParam("projectNo") Integer projectNo,
                                 @RequestParam("tagNo") Integer tagNo,
                                 @RequestParam("content") String content,
                                 Model model) {

        model.addAttribute("projectNo", projectNo);
        model.addAttribute("tagNo", tagNo);
        model.addAttribute("content", content);

        return "tag/tagModify";
    }

    @PostMapping("/modify")
    public String tagModify(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("tagNo") Integer tagNo,
                            @RequestParam("content") String content) {

        tagService.modifyTag(tagNo, content);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/delete")
    public String tagDelete(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("tagNo") Integer tagNo) {

        tagService.deleteTag(tagNo);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/task/register")
    public String taskTagRegister(@RequestParam("projectNo") Integer projectNo,
                                  @RequestParam("taskNo") Integer taskNo,
                                  @RequestParam("tagNo") Integer tagNo) {

        tagService.addTaskTag(taskNo, tagNo);

        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }
}
