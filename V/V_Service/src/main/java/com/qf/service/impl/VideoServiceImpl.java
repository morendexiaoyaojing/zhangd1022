package com.qf.service.impl;

import com.qf.dao.VideoDao;
import com.qf.entity.QueryVo;
import com.qf.entity.Video;
import com.qf.service.VideoService;
import com.qf.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource(name = "videoDao")
    private VideoDao videoDao;
    @Override
    public Page<Video> findAll(QueryVo queryVo) {
        // 设置查询条件,从哪一条数据开始查
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

        // 查询数据结果集
        List<Video> list = videoDao.findAll(queryVo);

        // 查询到的数据总条数
        Integer total = videoDao.getCount();

        // 封装返回的page对象
        Page<Video> videoPage = new Page<Video>();
        //查询数据
        videoPage.setRows(list);
        //当前页
        videoPage.setPage(queryVo.getPage());
        //每页数据
        videoPage.setSize(queryVo.getRows());
        //总记录数
        videoPage.setTotal(total);

        return videoPage;
    }

    @Override
    public Integer getCount() {
        return videoDao.getCount();
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoDao.findVideoById(id);
    }

    @Override
    public Video findVideosById(Integer id) {
        return videoDao.findVideosById(id);
    }

    @Override
    public void updateVideoById(Video video) {
            videoDao.updateVideoById(video);
    }

    @Override
    public void deleteVideoById(Integer id) {
        videoDao.deleteVideoById(id);
    }

    @Override
    public void addVideo(Video video) {
            videoDao.addVideo(video);
    }





    ///////////////////////////////////////


}
