package com.super404.video.controller;

import com.super404.video.domain.Video;
import com.super404.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("page")
    public Object pageVideo(){
        return videoService.findAll();
    }

    @GetMapping("find_by_id")
    public Object findById(int videoId){
        return videoService.findById(videoId);
    }

    @DeleteMapping("del_by_id")
    public Object delById(int videoId){
        return videoService.delete(videoId);
    }

    @PutMapping("update_by_id")
    public Object update(int videoId, String title){
        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);
        return videoService.update(video);
    }

    @PostMapping("save")
    public Object save(String title){
        Video video = new Video();
        video.setTitle(title);
        return videoService.save(video);
    }



}
