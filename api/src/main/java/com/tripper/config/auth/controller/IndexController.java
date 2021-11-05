package com.tripper.config.auth.controller;

import com.tripper.config.auth.LoginUser;
import com.tripper.config.auth.dto.SessionUser;
import com.tripper.config.auth.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor   //이게 scanBasePackage도 아니고 이걸 만들어야지 생성자가 생겨서 찾을수 있나보다??
@Controller
public class IndexController {
    private final PostService postService;
    private final HttpSession httpSession;

    @RequestMapping("/")
    public String goHome(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
}
