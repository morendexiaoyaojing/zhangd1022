package com.qf.controller;

import com.qf.entity.Subject;
import com.qf.entity.User;
import com.qf.service.SubjectService;
import com.qf.service.UserService;
import com.qf.utils.ImageCut;
import com.qf.utils.MailUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "subjectService")
    private SubjectService subjectService;

    //主页
    @RequestMapping("/loginOut")
    public ModelAndView aaa(ModelAndView modelAndView){
        List<Subject> subjectList=subjectService.getAll();
        modelAndView.addObject("subjectList",subjectList);
        modelAndView.setViewName("/WEB-INF/jsp/before/index.jsp");
        return modelAndView;
    }

    @RequestMapping("/loginOut2")
    public ModelAndView bbb(ModelAndView modelAndView){
        List<Subject> subjectList=subjectService.getAll();
        modelAndView.addObject("subjectList",subjectList);
        modelAndView.setViewName("/WEB-INF/jsp/before/index.jsp");
        return modelAndView;
    }
//登录
    @RequestMapping("/loginUser")
    public void loginUser(User user, HttpServletResponse response,HttpServletRequest request) throws  Exception{
        User us=userService.findUser(user);
        request.getSession().setAttribute("user",us);
        request.getSession().setAttribute("userAccount",user.getEmail());
        if(us!=null){
            response.getWriter().print("success");
        }
    }
    //个人中心
    @RequestMapping("/showMyProfile")
    public ModelAndView  showMyProfile(ModelAndView modelAndView,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
       modelAndView.setViewName("/WEB-INF/jsp/before/my_profile.jsp");
       return modelAndView;
    }
//注册
    @RequestMapping("/insertUser")
    public void insertUser(HttpServletRequest request,HttpServletResponse response) throws Exception{

        String email=request.getParameter("email");
        String password=request.getParameter("password");
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.addUser(user);
        request.getSession().setAttribute("user",user);
        response.getWriter().print("success");
    }
    //验证邮箱是否重复
    @RequestMapping("/validateEmail")
    public void validateEmail(String email,HttpServletResponse response,HttpServletRequest request) throws Exception{

        String email1=userService.findByEmail(email);
        if(email1!=null){
            response.getWriter().print("false");
        }else{
            response.getWriter().print("success");
        }
    }

    //更改资料的地址
    @RequestMapping("/changeProfile")
    public String changeProfile(){
        return "/WEB-INF/jsp/before/change_profile.jsp";
    }
    //更改资料的操作
    @RequestMapping("/updateUser")
    public void updateUser(User user, HttpServletResponse response,HttpServletRequest request) throws  Exception{
            //User user= (User) request.getSession().getAttribute("user");

            userService.updateUser(user);
            User user1= (User) userService.findAll(user.getId());
             request.getSession().setAttribute("user",user1);
            response.sendRedirect(request.getContextPath()+"/user/showMyProfile");
            //return "redirect:/user/showMyProfile";
    }
//更改头像
    @RequestMapping("/changeAvatar")
    public String changeAvatar(){
        return "/WEB-INF/jsp/before/change_avatar.jsp";
    }
    //更换头像页面的操作
    @RequestMapping("/upLoadImage")
    public String upLoadImage(@RequestParam("image_file") MultipartFile image_file,String x1, String x2, String y1, String y2, HttpServletRequest request)throws IOException {
        System.out.println("springmvc文件上传...");

        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = "E:/upload";
        // 判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdirs();
        }

        // 说明上传文件项
        // 获取上传文件的名称
        String filename = image_file.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;

        // 完成文件上传
        image_file.transferTo(new File(path,filename));
        int x1Int = (int) Double.parseDouble(x1);
        int x2Int = (int) Double.parseDouble(x2);
        int y1Int = (int) Double.parseDouble(y1);
        int y2Int = (int) Double.parseDouble(y2);
        new ImageCut().cutImage(path + "/" + filename, x1Int, y1Int, x2Int - x1Int, y2Int - y1Int);
        User user= (User) request.getSession().getAttribute("user");
        user.setImgUrl(filename);
            userService.upLoadImage(user);
        request.getSession().setAttribute("user",user);
        return "/user/showMyProfile";
    }
    //密码安全
    @RequestMapping("/passwordSafe")
    public String passwordSafe(){
        return "/WEB-INF/jsp/before/password_safe.jsp";
    }
    //密码安全页面的操作
    @RequestMapping("/updatePassword")
    public void updatePassword(HttpServletRequest request,HttpServletResponse response)throws Exception{

        User user= (User) request.getSession().getAttribute("user");
        user.setPassword(request.getParameter("newPassword"));
        userService.updatePassword(user);
        request.getSession().setAttribute("user",user);
        response.sendRedirect(request.getContextPath()+"/user/showMyProfile");
    }
    //密码安全页面的对比密码是否正确的操作
    @RequestMapping("/validatePassword")
    public void validatePassword(String password,HttpServletRequest request,HttpServletResponse response)throws Exception{
        System.out.println(password);
        User user= (User) request.getSession().getAttribute("user");
        userService.updateUser(user);
       if(password.equals(user.getPassword())){
            response.getWriter().print("success");
        }
    }

    //忘记密码
    @RequestMapping("/forgetPassword")
    public String forgetPassword(){
        return "/WEB-INF/jsp/before/forget_password.jsp";
    }
    //忘记密码页面的操作
    @RequestMapping("/validateEmailCode")
    public ModelAndView validateEmailCode(String email, String code,ModelAndView model){
        User user = userService.getOne(email);
        boolean key=false;
        if(code!=null && !code.equals("")){
            key = user.getCode().equals(code);
        }
        System.out.println(key);
        if(key){
            model.addObject("email",email);
            model.setViewName("/WEB-INF/jsp/before/reset_password.jsp");
        }else{
            model.addObject("email",email);
            model.setViewName("/WEB-INF/jsp/before/forget_password.jsp");
        }
        return model;
    }
    //邮件验证
    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String email){

        MailUtils mailUtils = new MailUtils();
        String code = mailUtils.getValidateCode(6);
        mailUtils.sendMail("1029005376@qq.com", "妹妹，哥哥厉不厉害", "测试邮件随机生成的验证码是："
                + code);
        mailUtils.sendMail("1029005376@qq.com", "妹妹，哥哥厉不厉害", "测试邮件随机生成的验证码是："
                + code);

        if(mailUtils.sendMail(email, "你好，这是一封验证码邮件，无需回复。", "测试邮件随机生成的验证码是："
                + code))
        {
            User user = userService.getOne(email);
            user.setCode(code);
            userService.upDateCode(user);
            return "success";
        }
        return "hasNoUser";
    }
    //重置密码
    @RequestMapping("/resetPassword")
    private ModelAndView resetPassword(String password02,String email,ModelAndView model){

        User user = userService.getOne(email);
        user.setPassword(password02);
        user.setCode("");
        userService.upDatePassWord(user);
        userService.upDateCode(user);
        model.addObject("user",user);
        model.setViewName("/WEB-INF/jsp/before/my_profile.jsp");
        return  model;
    }

}
