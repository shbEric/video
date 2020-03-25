package com.super404.video.controller.admin;

import com.super404.video.domain.Video;
import com.super404.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员video接口
 */
@RestController
@RequestMapping("/admin/api/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据Id删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id",required = true) int videoId){
        return videoService.delete(videoId);
    }

    /**
     * 根据Id更新视频
     * @param video
     * @return
     */
    @PutMapping("update_by_id")
    public Object update(@RequestBody Video video){
        return videoService.update(video);
    }

    /**
     * 保存视频对象
     * @param video
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody Video video){
        return videoService.save(video);
    }
}
