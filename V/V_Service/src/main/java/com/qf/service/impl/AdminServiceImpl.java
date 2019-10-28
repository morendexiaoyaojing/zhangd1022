package com.qf.service.impl;

import com.qf.dao.AdminDao;
import com.qf.entity.Admin;
import com.qf.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    @Override
    public Admin findAdmin(Admin admin) {
        return adminDao.findAdmin(admin);
    }
}
