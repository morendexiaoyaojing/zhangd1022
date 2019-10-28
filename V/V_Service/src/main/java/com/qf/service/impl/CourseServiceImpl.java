package com.qf.service.impl;

import com.qf.dao.CourseDao;
import com.qf.entity.Course;
import com.qf.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource(name = "courseDao")
    private CourseDao courseDao;
    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public Course findAllById(String id) {
        return courseDao.findAllById(id);
    }
}
