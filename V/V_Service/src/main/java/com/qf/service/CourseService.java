package com.qf.service;

import com.qf.entity.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getAll();


    public Course findAllById(String id);
}
