package com.qf.controller;

import com.qf.entity.Course;
import com.qf.entity.Speaker;
import com.qf.entity.Subject;
import com.qf.entity.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Resource(name = "videoService")
    private VideoService videoService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name="speakerService")
    private SpeakerService speakerService;

    @Resource(name = "subjectService")
    private SubjectService subjectService;



//显示首页下高端课程
    @RequestMapping("/course/{id}")
    public ModelAndView course(@PathVariable(value = "id")String id,ModelAndView modelAndView,String emails){
        List<Subject> subjectList=subjectService.getAll();
        Subject subject=subjectService.findAllById(Integer.parseInt(id));
        modelAndView.addObject("emails",emails);
        modelAndView.addObject("subject",subject);
        System.out.println(subject);
        modelAndView.addObject("subjectList",subjectList);
        modelAndView.setViewName("/WEB-INF/jsp/before/course.jsp");
        return modelAndView;
    }

    //显示高端课程里面的内容
    @RequestMapping("/showVideo")
    public ModelAndView showVideo(String videoId,ModelAndView modelAndView,String speaker_name){
        List<Subject> subjectList=subjectService.getAll();
        Video video=videoService.findVideosById(Integer.parseInt(videoId));
        Course course=courseService.findAllById(videoId);
        modelAndView.addObject("subjectList",subjectList);
        modelAndView.addObject("video",video);
        modelAndView.addObject("course",course);
        modelAndView.addObject("speaker_name",speaker_name);
        modelAndView.setViewName("/WEB-INF/jsp/before/section.jsp");
        return  modelAndView;
    }

    //视频次数
    @RequestMapping("/updatePlayNum")
    public ModelAndView updatePlayNum(ModelAndView modelAndView,String playNum,Integer id){
        Video video=new Video();
        video.setPlay_num(Integer.parseInt(playNum)+1);
        videoService.updateVideoById(video);
        modelAndView.setViewName("/WEB-INF/jsp/before/section.jsp");
        return modelAndView;
    }
}
