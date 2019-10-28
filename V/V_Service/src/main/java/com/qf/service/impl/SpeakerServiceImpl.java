package com.qf.service.impl;

import com.qf.dao.SpeakerDao;
import com.qf.entity.QueryVo;
import com.qf.entity.Speaker;
import com.qf.service.SpeakerService;
import com.qf.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    @Resource(name = "speakerDao")
    private SpeakerDao speakerDao;


    @Override
    public Page<Speaker> findAll(QueryVo queryVo) {
        // 设置查询条件,从哪一条数据开始查
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

        // 查询数据结果集
        List<Speaker> list = speakerDao.findAll(queryVo);

        // 查询到的数据总条数
        Integer total = speakerDao.getCount();

        // 封装返回的page对象
        Page<Speaker> speakerPage = new Page<Speaker>();
        //查询数据
        speakerPage.setRows(list);
        //当前页
        speakerPage.setPage(queryVo.getPage());
        //每页数据
        speakerPage.setSize(queryVo.getRows());
        //总记录数
        speakerPage.setTotal(total);

        return speakerPage;
    }

    @Override
    public Speaker getSpeakerById(Integer id){
        return speakerDao.findSpeakerById(id);
    }

    @Override
    public Integer getCount() {
        return speakerDao.getCount();
    }
    //修改编辑
    @Override
    public void updateSpeakerById(Speaker speaker) {
        speakerDao.updateSpeakerById(speaker);
    }
    //删除
    @Override
    public void deleteSpeakerById(Integer id) {
        speakerDao.deleteSpeakerById(id);

    }

    //增加
    @Override
    public void addSpeaker(Speaker speaker) {
        speakerDao.addSpeaker(speaker);
    }

    @Override
    public List<Speaker> getAll() {
        return speakerDao.getAll();
    }
}
