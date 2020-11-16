package com.ssm.controller;

import com.ssm.entity.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {



    @RequestMapping("/toLogin")
    public  String  test(){
        return "login";
    }

    @RequestMapping("/User")
    public String User(){return "ownCustomer";}

    @RequestMapping("/toRegister")
    public  String toRegister(){
        return "register";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/user/login")
    public String login(Employee emp, HttpServletRequest request){
        request.getSession().setAttribute("emp",emp);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(emp.getUsername(), emp.getPassword());
        try {
            subject.login(token);
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }
    }

//    @RequestMapping("/register")
//    @ResponseBody
//    public String Register(Admin admin){
//        if(adminService.selectByName(admin.getUsername())!=null){
//          return "false";
//        }
//        else{
//            adminService.addAmin(admin);
//            return "true";
//        }
//
//    }
}