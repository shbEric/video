package com.super404.video.controller;

import com.super404.video.config.WeChatConfig;
import com.super404.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("test")
    public String test(){
        System.out.println("测试热部署");
        return "hello world";
    }

    @Autowired
    private WeChatConfig weChatConfig;

    @RequestMapping("test_config")
    public String testConfig(){
        System.out.println(weChatConfig.getAppId());
        return "hello world";
    }

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("test_db")
    public Object testDB(){
        return videoMapper.findAll();
    }
}
