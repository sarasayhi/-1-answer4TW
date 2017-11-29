package com.Marissa.FAQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class testController {
    @RequestMapping("/test")
    public String entry(){
        return "index";
    }

    @RequestMapping("/t")
    @ResponseBody
    public String t(){
        return "index";
    }
}
