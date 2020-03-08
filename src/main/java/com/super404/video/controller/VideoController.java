package com.super404.video.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    @RequestMapping("test")
    public String test(){
        System.out.println("测试热部署");
        return "hello world";
    }
}
