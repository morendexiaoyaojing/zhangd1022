package com.qf.service;

import com.qf.entity.QueryVo;
import com.qf.entity.Speaker;
import com.qf.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
public interface SpeakerService {

    public Page<Speaker> findAll(QueryVo queryVo);
    //查询总记录数
    public Integer getCount();

    public Speaker getSpeakerById(Integer id);

    //根据id编辑客户
    void updateSpeakerById(Speaker speaker);

    //根据id删除用户
    void deleteSpeakerById(Integer id);

    //添加用户
    void addSpeaker(Speaker speaker);

    List<Speaker> getAll();
}
