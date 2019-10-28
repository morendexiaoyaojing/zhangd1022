package com.qf.controller;

import com.qf.entity.QueryVo;
import com.qf.entity.Speaker;
import com.qf.service.SpeakerService;
import com.qf.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



    @Controller
    public class SpeakerController {

        @Resource(name = "speakerService")
        private SpeakerService speakerService;

        @RequestMapping("/speakerList")
        public ModelAndView speakerList(ModelAndView model, QueryVo queryVo) {

            // 分页查询数据
            Page<Speaker> page = speakerService.findAll(queryVo);
            // 把分页查询的结果放到模型中
            model.addObject("page", page);

            model.setViewName("WEB-INF/jsp/behind/speakerList.jsp");

            return model;
        }

        @RequestMapping("/getSpeakerById")
        public ModelAndView getSpeakerById(Integer id, ModelAndView model) {
            System.out.println("ajax测试");
            if(id!=null){
                Speaker speaker = speakerService.getSpeakerById(id);
                model.addObject("speaker",speaker);
                System.out.println(speaker + "编辑回显-----");
            }
            model.setViewName("WEB-INF/jsp/behind/addSpeaker.jsp");
            return model;
        }

        @RequestMapping("/updateSpeaker")
        public void updateSpeakerById(Speaker speaker, HttpServletRequest request, HttpServletResponse response) throws IOException {

            speaker.setSpeaker_name(request.getParameter("speakerName"));
            speaker.setSpeaker_desc(request.getParameter("speakerDesc"));
            speaker.setSpeaker_job(request.getParameter("speakerJob"));
            if(speaker.getId()!=null){
                System.out.println("更新的方法");
                speakerService.updateSpeakerById(speaker);
            }else {
                speakerService.addSpeaker(speaker);
            }
            response.sendRedirect(request.getContextPath()+"/speakerList");
        }

        @RequestMapping("/deleteSpeaker")
        @ResponseBody
        public String deleteSpeakerById(Integer id) {
            speakerService.deleteSpeakerById(id);
            System.out.println("删除方法");
            return "success";
        }
    }
