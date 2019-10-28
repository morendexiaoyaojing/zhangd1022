package com.qf.dao;

import com.qf.entity.QueryVo;
import com.qf.entity.Video;

import java.util.List;

public interface VideoDao {

    public List<Video> findAll(QueryVo queryVo);

    public Video findVideoById(Integer id);

    public Video findVideosById(Integer id);
    //查询总记录数
    public Integer getCount();

    //删除
    public void deleteVideoById(Integer id);

    //修改
    public void updateVideoById(Video video);

    //增加
    public void addVideo(Video video);






    /////////////////////////////////////////
    //根据video的id 查询信息，提供给section
    public Video findAllBuId(Integer id);
}
