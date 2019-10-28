package com.qf.dao;

import com.qf.entity.QueryVo;
import com.qf.entity.Speaker;

import java.util.List;

public interface SpeakerDao {

    List<Speaker> findAll(QueryVo queryVo);
    //查询总记录数
    public Integer getCount();

    Speaker findSpeakerById(Integer id);

    //根据id编辑客户
    void updateSpeakerById(Speaker speaker);

    //根据id删除用户
    void deleteSpeakerById(Integer id);

    //添加用户
    void addSpeaker(Speaker speaker);

    List<Speaker> getAll();
}
