package com.qf.dao;

import com.qf.entity.Course;

import java.util.List;

public interface CourseDao {

    public List<Course> getAll();



    //供section页面使用的查询方法
    public Course findAllById(String id);
}
