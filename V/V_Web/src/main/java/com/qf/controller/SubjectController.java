package com.qf.controller;

import com.qf.entity.QueryVo;
import com.qf.entity.Subject;
import com.qf.service.SubjectService;
import com.qf.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SubjectController {
    @Resource(name = "subjectService")
    private SubjectService subjectService;

    @RequestMapping("/subjectList")
    public ModelAndView subjectList(ModelAndView model, QueryVo queryVo){
        Page<Subject> page=subjectService.findAll(queryVo);
        model.addObject("page",page);
        model.setViewName("WEB-INF/jsp/behind/subjectList.jsp");
        return model;
    }

    @RequestMapping("/getSubjectById")
    public ModelAndView getSubjectById(ModelAndView model,Integer id){
        if(id!=null){
            Subject subject=subjectService.findSubjectById(id);
            model.addObject("subject",subject);
        }
        model.setViewName("WEB-INF/jsp/behind/addSubject.jsp");
        return model;
    }

    @RequestMapping("/updateSubjectById")
    public void updateSubjectById(Subject subject, HttpServletResponse response, HttpServletRequest request) throws  Exception{
        subject.setSubject_name(request.getParameter("subjectName"));
        if(subject.getId()!=null){
            subjectService.updateSubjectById(subject);
        }else{
            subjectService.addSubject(subject);
        }
        response.sendRedirect(request.getContextPath()+"/subjectList");
    }

    @RequestMapping("/deleteSubject")
    public void deleteSubjectById(Integer id,HttpServletResponse response) throws Exception{
        subjectService.deleteSubjectById(id);
        response.getWriter().print("success");
    }

}
