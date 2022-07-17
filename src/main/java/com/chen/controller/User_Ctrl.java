package com.chen.controller;

import com.chen.dao.Log_Dao;
import com.chen.dao.User_Dao;
import com.chen.entity.Log;
import com.chen.entity.User;
import com.chen.service.MailService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class User_Ctrl {

    @Resource
    User_Dao user_dao;

    @Resource
    Log_Dao log_dao;

    @Resource
    MailService mailService;

    //* 注册 默认只能注册为用户，不能注册为管理员
    @RequestMapping("/user_register")
    @ResponseBody
    public String register(Model mv,String username,String password,Long user_phone,String email){
        System.out.println("访问了这里");
        System.out.println("username="+username+" password="+password+" user_phone="+user_phone+" email="+email);
        String feedback = "没有东西";   //用于返回注册是否成功

        User ce01 = user_dao.select_User(user_phone,username,email);
        System.out.println("ce="+ce01);
        if(ce01==null){
            password = encrypt(password);
            User user = new User(username,password,user_phone,email,"用户");
            System.out.println("user="+user);
            int i = user_dao.insert_User(user);
            if(i==1){
                feedback = "注册成功,请返回登录页面";
            }else {
                feedback = "注册失败，请等待3分钟再注册";
            }
        }else {
            feedback = ce01.getUsername().equals(username)?"用户名重复":"电话号码已经被注册";
            if(ce01.getEmail().equals(email)){
                feedback = "邮箱已经被注册";
            }

        }
        mv.addAttribute("feedback",feedback);
        return feedback;
    }

    //*登录 一个是游客登录，一个是用户登录
    @RequestMapping("index")
    public String login(Model mv,String username, String password,String status,HttpServletRequest request){
        System.out.println("username="+username);
        HttpSession session = request.getSession();
        String feedback="";
        if(!"".equals(username) && "用户登录".equals(status)){
            User user = user_dao.select_User((long)0,username,null);
            System.out.println(user);
            if(user!=null){
                String userPassword = decrypt(user.getPassword());
                if(userPassword.equals(password)){
                    session.setAttribute("username",username);
                    status = user.getType().equals("管理员")?"管理员":"用户";
                    session.setAttribute("status",status);
                    return "index";
                }else {
                    feedback="登录信息反馈 : 密码错误";
                    mv.addAttribute("feedback",feedback);
                    return "login";
                }
            }else {
               feedback="登录信息反馈 : 没有该用户";
            }
        }
        if("游客浏览".equals(status)){
            System.out.println("22222222222");
            session.setAttribute("status","游客");
            return "index";
        }
        mv.addAttribute("feedback",feedback);
        return "login";
    }

    //登录页面
    @RequestMapping({"","login"})
    public String login(){
        return "login";
    }

    //退出登录
    @RequestMapping("/quit")
    public String quit( String username, HttpSession session){
        System.out.println("username="+username);
//        System.out.println("222username="+session.getAttribute("username"));
        session.removeAttribute("username");
        session.setAttribute("status","游客");
        return "index";
    }

    //*返回用户账号信息页面
    @RequestMapping("user_account")
    public String user_account(Model mv,HttpSession session){
        System.out.println("用户账号信息");
        String username = (String) session.getAttribute("username");
        System.out.println("username="+username);
        User user = user_dao.select_User((long) 0,username,null);
        user.setPassword(decrypt(user.getPassword()));
        mv.addAttribute("user",user);
        System.out.println(user);
        return "user_account";
    }

    //*查看用户下载日志
    @RequestMapping("download_message")
    @ResponseBody
    public Map download_message(Integer pageNum, Integer pageSize){
        System.out.println("下载日志");
        System.out.println("pagenum= "+pageNum);
        System.out.println("pageSize= "+pageSize);
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<Log> list = log_dao.select_All();
        map.put("list",list);
        map.put("Allsize",log_dao.select_All().size());
        return map;
    }

    //修改用户密码或者手机号、邮箱
    @RequestMapping("updateMsg")
    @ResponseBody
    public String updateMsg(String password,Long phone,String email,HttpSession session){
        System.out.println("psd="+password);
        System.out.println("Phone="+phone);
        System.out.println("email="+email);
        String message = "";
        if(password!=null){
            password = decrypt(password);
        }
        int i = user_dao.update_User((String)session.getAttribute("username"),password,phone,email);
        if(i==1){
            message = "修改成功";
        }
        return message;
    }

    //找回密码
    @RequestMapping("back_pwd")
    @ResponseBody
    public User back_pwd(Long phone,String email){
        System.out.println("Phone="+phone+" email="+email);
        User user = user_dao.back_psd(phone,email);
        if(user!=null){
            System.out.println("user="+user);
            mailService.sendSimpleMail(user.getEmail(),"环境质量在线监控系统","用户名: "+user.getUsername()+"\n密码："+user.getPassword());
        }else {
            user = new User();
            user.setPassword("没有该用户");
            System.out.println("没找到");
        }
        return user;
    }

    //* 密码加密,不用
    public String encrypt(String password){
        /*char[] encrypt_rute = "monitor".toCharArray();
        char[] chars_pwd = password.toCharArray();
        for(int k=0; k<password.length(); k++){
            chars_pwd[k] = (char) (chars_pwd[k]+encrypt_rute[k]);
            chars_pwd[k] = (char) (chars_pwd[k]+encrypt_rute[k%encrypt_rute.length]);
            System.out.println("1111="+chars_pwd[k]);
            System.out.println("dddd="+encrypt_rute[k%encrypt_rute.length]);
        }
        return new String(chars_pwd);*/
        return password;
    }

    //* 密码解密,不用
    public String decrypt(String password){
        /*char[] encrypt_rute = "monitor".toCharArray();
        char[] chars_pwd = password.toCharArray();
        for(int k=0; k<password.length(); k++){
            chars_pwd[k] = (char) (chars_pwd[k]-encrypt_rute[k%encrypt_rute.length]);
            System.out.println("1111="+chars_pwd[k]);
        }
        return new String(chars_pwd);*/
        return password;
    }

   /* public static void main(String[] args) {
        User_Ctrl user_ctrl = new User_Ctrl();
        String a = user_ctrl.encrypt("123123dfdfsf");
        System.out.println("a="+a);
        System.out.println("---------------------");
        System.out.println(user_ctrl.decrypt(a));
    }*/

}
