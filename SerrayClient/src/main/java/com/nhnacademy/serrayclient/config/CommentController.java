package com.nhnacademy.serrayclient.config;

import com.nhnacademy.serrayclient.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;

    @PostMapping("/register")
    public String commentRegister(@RequestParam("taskNo") Integer taskNo,
                                  @RequestParam("content") String content,
                                  @RequestParam("projectNo") Integer projectNo,
                                  Principal principal) {

        service.registerComment(taskNo, principal.getName(), content);

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
                                @RequestParam("content") String content) {

        service.modifyComment(commentNo, content);
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
