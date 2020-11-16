package com.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Employee;
import com.ssm.entity.Visit;
import com.ssm.service.CustomerService;
import com.ssm.service.VisitService;
import com.ssm.service.EmpService;
import com.ssm.service.ShareService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Visit")
public class VisitController {

    @Autowired
    VisitService service;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmpService empService;

    @Autowired
    ShareService shareService;

    @RequestMapping("toVisit")
    public String toVisit(){return "visit";}

    @RequestMapping("viewVisit")
    @ResponseBody
    public Map viewVisit(@RequestParam(value = "page",defaultValue = "1")int page){
        PageHelper.startPage(page,4);
        List<Visit> Visits = service.selectAll();
        PageInfo pages = new PageInfo(Visits);
        Map map = new HashMap();
        map.put("rows",pages.getList());
        map.put("total",pages.getTotal());
        return map;
    }

    @RequestMapping("/visitor")
    @ResponseBody
    public String visitor(){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        if (emp.getRoleid()==1){return "/Visit/empVisit";}
        else return "/Visit/viewVisit";
    }

    @RequestMapping("/empVisit")
    @ResponseBody
    public Map visit(@RequestParam(value = "page",defaultValue = "1")int page){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        Integer empid = emp.getEmpid();
        PageHelper.startPage(page,4);
        List<Visit> Visits = service.selectByEmpId(empid);
        PageInfo pages = new PageInfo(Visits);
        Map map = new HashMap();
        map.put("rows",pages.getList());
        map.put("total",pages.getTotal());
        return map;
    }

    @RequestMapping("/VisitById")
    @ResponseBody
    public String VisitById(@RequestParam(value = "page",defaultValue = "1")int page,int id){
        PageHelper.startPage(page,4);
        List<Visit> Visits = service.selectById(id);
        PageInfo pages = new PageInfo(Visits);
        return JSON.toJSONString(pages);
    }


    @RequestMapping("/addVisit")
    @ResponseBody
    public String addVisit(Visit v){
        Employee emp = (Employee) SecurityUtils.getSubject().getPrincipal();
        v.setEmpid(emp.getEmpid());
        v.setUsername(emp.getUsername());
            int add = service.add(v);
            if (add>0) return "true";
            return "false";
    }

    @RequestMapping("/updateVisit")
    @ResponseBody
    public String updateVisit(Visit v){
        int update = service.update(v);
        if (update>0) return "true";
        return "false";
    }


}
