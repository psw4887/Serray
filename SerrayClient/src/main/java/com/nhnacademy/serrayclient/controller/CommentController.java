package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.vo.CommentForm;
import com.nhnacademy.serrayclient.exception.ValidException;
import com.nhnacademy.serrayclient.service.CommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;

    @PostMapping("/register")
    public String commentRegister(@RequestParam("taskNo") Integer taskNo,
                                  @RequestParam("projectNo") Integer projectNo,
                                  @Valid @ModelAttribute CommentForm form,
                                  BindingResult bindingResult,
                                  Principal principal) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.registerComment(taskNo, principal.getName(), form.getContent());

        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }

    @GetMapping("/modify")
    public String readyCommentModify(@RequestParam("taskNo") Integer taskNo,
                                     @RequestParam("commentNo") Integer commentNo,
                                     @RequestParam("projectNo") Integer projectNo,
                                     @RequestParam("content") String content,
                                     Principal principal,
                                     Model model) {

        if(!principal.getName().equals(service.getCommenter(commentNo))) {
            return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
        }

        model.addAttribute("taskNo", taskNo);
        model.addAttribute("commentNo", commentNo);
        model.addAttribute("content", content);
        model.addAttribute("projectNo", projectNo);

        return "comment/commentModify";
    }

    @PostMapping("/modify")
    public String commentModify(@RequestParam("taskNo") Integer taskNo,
                                @RequestParam("commentNo") Integer commentNo,
                                @RequestParam("projectNo") Integer projectNo,
                                @Valid @ModelAttribute CommentForm form,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.modifyComment(commentNo, form.getContent());
        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }

    @GetMapping("/delete")
    public String commentDelete(@RequestParam("taskNo") Integer taskNo,
                                @RequestParam("commentNo") Integer commentNo,
                                @RequestParam("projectNo") Integer projectNo,
                                Principal principal) {

        if(principal.getName().equals(service.getCommenter(commentNo))) {
            service.deleteComment(commentNo);
        }

        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }
}
