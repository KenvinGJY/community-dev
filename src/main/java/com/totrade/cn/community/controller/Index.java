package com.totrade.cn.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Index {
    @GetMapping("/index")
    public  String hellow(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }
}
