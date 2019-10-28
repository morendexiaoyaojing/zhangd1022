package com.qf.dao;

import com.qf.entity.QueryVo;
import com.qf.entity.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll(QueryVo queryVo);
    //查询总记录数
    public Integer getCount();

    Subject findSubjectById(Integer id);

    //根据id编辑客户
    void updateSubjectById(Subject subject);

    //根据id删除用户
    void deleteSubjectById(Integer id);

    //添加用户
    void addSubject(Subject subject);

    List<Subject> getAll();


    //查询跳转course所需的条件   根据subject的id查询couse的内容
    Subject findAllById(Integer id);



}
