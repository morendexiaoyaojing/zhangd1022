package com.qf.controller;

import com.qf.entity.Course;
import com.qf.entity.QueryVo;
import com.qf.entity.Speaker;
import com.qf.entity.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.VideoService;
import com.qf.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class VideoController {
    @Resource(name = "videoService")
    private VideoService videoService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name="speakerService")
    private SpeakerService speakerService;


    @RequestMapping("/videoList")
    public ModelAndView videoList(ModelAndView model, QueryVo queryVo){
        List<Speaker> speakerList=speakerService.getAll();
        List<Course> courseList=courseService.getAll();
        model.addObject("speakerList",speakerList);
        model.addObject("courseList",courseList);


        Page<Video> page=videoService.findAll(queryVo);
        model.addObject("page",page);
        model.setViewName("WEB-INF/jsp/behind/videoList.jsp");

        model.addObject("speakerid",queryVo.getSpeakerid());
        model.addObject("courseid",queryVo.getCourseid());
        return model;
    }

    @RequestMapping("/getVideoById")
    public ModelAndView getVideoById(Integer id,ModelAndView model){
            List<Course> courseList=courseService.getAll();
            List<Speaker> speakerList=speakerService.getAll();
            model.addObject("courseList",courseList);
            model.addObject("speakerList",speakerList);

            if(id!=null){
                Video video=videoService.getVideoById(id);
                model.addObject("video",video);
            }
            model.setViewName("WEB-INF/jsp/behind/addVideo.jsp");
            return model;
    }


    @RequestMapping("/updateVideo")
    public ModelAndView updateVideo(Video video,ModelAndView model){
            if(video.getId()!=null){
                    videoService.updateVideoById(video);
            }else{
                videoService.addVideo(video);
            }
            model.setViewName("/videoList");
            return model;
    }


    @RequestMapping("/deleteVideo")
    public void deleteVideoById(Integer id, HttpServletResponse response) throws  Exception{
            videoService.deleteVideoById(id);
            response.getWriter().print("success");
    }

    @RequestMapping("/delBatchVideos")
    public ModelAndView deleteVideo(Integer[] ids,ModelAndView model) {
        for(int i=0;i<ids.length;i++){
            System.out.println(ids[i]);
            videoService.deleteVideoById(ids[i]);
        }
        System.out.println("删除方法");
        model.setViewName("/videoList");
        return model;
    }






}
