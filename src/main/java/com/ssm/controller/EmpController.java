package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Employee;
import com.ssm.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("emp")
public class EmpController {


    @Autowired
    EmpService empService;

    @RequestMapping("/information")
    public String toinformation(){
        return "/UserInformation";
    }

    @RequestMapping("/getName")
    @ResponseBody
    public String getName(HttpServletRequest request){
        Employee emp = (Employee) request.getSession().getAttribute("emp");
        return JSON.toJSONString(emp);
    }

    
    @RequestMapping("/del")
    @ResponseBody
    public String del(@RequestParam(value = "empid") int empid){
        int delete = empService.delete(empid);
        if(delete!=0){return "true";}
        return "false";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Employee emp){
        if(empService.SelectByName(emp.getUsername())!=null){return "false";}
        int insert = empService.insert(emp);
        if(insert!=0){return "true";}
        else return "false";
    }

    @RequestMapping("/SelectByCurrentId")
    @ResponseBody
    public String SelectByCurrentId(@RequestParam(value = "page", defaultValue = "1")  int page,HttpServletRequest request){
        Employee emp = (Employee) request.getSession().getAttribute("emp");
        Integer empid = empService.SelectByUserName(emp.getUsername()).getEmpid();
        PageHelper.startPage(page,4);
        List<Employee> emp1 = empService.SelectById(empid);
        PageInfo pages = new PageInfo(emp1);
        return JSON.toJSONString(pages);

    }


    @RequestMapping("/SelectById")
    @ResponseBody
    public String SelectById(@RequestParam(value = "page", defaultValue = "1")  int page,
                                    @RequestParam(value = "empid") Integer empid){
        PageHelper.startPage(page,4);
        List<Employee> emp = empService.SelectById(empid);
        PageInfo pages = new PageInfo(emp);
        return JSON.toJSONString(pages);

    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Employee emp){
        int update = empService.update(emp);
        if (update>0)return "true";
        return "false";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam(value = "password") String password, HttpServletRequest request){
        Employee emp = (Employee) request.getSession().getAttribute("emp");
        Integer empid = empService.SelectByUserName(emp.getUsername()).getEmpid();
        int i = empService.updatePassword(empid,password);
        if (i>0) return "true";
        return "false";
    }
}
