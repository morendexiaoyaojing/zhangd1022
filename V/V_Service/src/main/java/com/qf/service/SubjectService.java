package com.qf.service;

import com.qf.entity.QueryVo;
import com.qf.entity.Subject;
import com.qf.utils.Page;

import java.util.List;

public interface SubjectService {
    public Page<Subject> findAll(QueryVo queryVo);
    //查询总记录数
    public Integer getCount();

    public Subject findSubjectById(Integer id);

    //根据id编辑客户
    void updateSubjectById(Subject subject);

    //根据id删除用户
    void deleteSubjectById(Integer id);

    //添加用户
    void addSubject(Subject subject);

    List<Subject> getAll();


    Subject findAllById(Integer id);
}
