package com.qf.service;

import com.qf.entity.QueryVo;
import com.qf.entity.Video;
import com.qf.utils.Page;

public interface VideoService {

    public Page<Video> findAll(QueryVo queryVo);

    public Integer getCount();

    public Video getVideoById(Integer id);

    public Video findVideosById(Integer id);

    public void updateVideoById(Video video);

    public void deleteVideoById(Integer id);

    public void addVideo(Video video);
}
