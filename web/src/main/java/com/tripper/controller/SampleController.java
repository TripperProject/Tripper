package com.tripper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/Hello")
    public String Home2() {
        return "/Sample";
    }
}
