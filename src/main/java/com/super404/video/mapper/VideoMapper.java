package com.super404.video.mapper;

import com.super404.video.domain.Video;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * video数据访问层
 */
public interface VideoMapper {
    @Select("select * from video")
    List<Video> findAll();
}
