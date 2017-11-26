package com.Marissa.FAQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("/")
    public String entry(){
        return "index";
    }
}
