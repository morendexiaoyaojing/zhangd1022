package com.qf.service.impl;

import com.qf.dao.SubjectDao;
import com.qf.entity.QueryVo;
import com.qf.entity.Subject;
import com.qf.service.SubjectService;
import com.qf.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
    @Resource(name = "subjectDao")
    private SubjectDao subjectDao;
    @Override
    public Page<Subject> findAll(QueryVo queryVo) {

        // 设置查询条件,从哪一条数据开始查
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

        // 查询数据结果集
        List<Subject> list = subjectDao.findAll(queryVo);

        // 查询到的数据总条数
        Integer total = subjectDao.getCount();

        // 封装返回的page对象
        Page<Subject> subjectPage = new Page<Subject>();
        //查询数据
        subjectPage.setRows(list);
        //当前页
        subjectPage.setPage(queryVo.getPage());
        //每页数据
        subjectPage.setSize(queryVo.getRows());
        //总记录数
        subjectPage.setTotal(total);

        return subjectPage;
    }

    @Override
    public Integer getCount() {
        return subjectDao.getCount();
    }

    @Override
    public Subject findSubjectById(Integer id) {
        return subjectDao.findSubjectById(id);
    }

    @Override
    public void updateSubjectById(Subject subject) {
            subjectDao.updateSubjectById(subject);
    }

    @Override
    public void deleteSubjectById(Integer id) {
            subjectDao.deleteSubjectById(id);
    }

    @Override
    public void addSubject(Subject subject) {
            subjectDao.addSubject(subject);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }


    /////////////////////////////////////////////
    @Override
    public Subject findAllById(Integer id) {
        return subjectDao.findAllById(id);
    }
}
