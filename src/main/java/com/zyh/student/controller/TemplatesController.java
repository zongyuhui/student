package com.zyh.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/templates")
public class TemplatesController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("name","Bob");
        return "hello";
    }
}
