package com.qf.controller;


import com.qf.entity.Admin;
import com.qf.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;

    @RequestMapping("/loginView")
    public String getLogin(){
        return "WEB-INF/jsp/behind/login.jsp";
    }

    @RequestMapping("/login")
    public void login(Admin admin, HttpServletResponse response) throws Exception{
        Admin ad=adminService.findAdmin(admin);
        if(ad!=null){
            response.getWriter().print("success");
        }
    }


}
