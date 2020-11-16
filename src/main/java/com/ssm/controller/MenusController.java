package com.ssm.controller;


import com.ssm.entity.Permission;
import com.ssm.entity.Employee;
import com.ssm.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenusController {

    @Autowired
    PermissionService service;

    @RequestMapping("/menus")
    @ResponseBody
    public List<Permission> menus(){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        List<Permission> menu = service.getMenu(emp.getRoleid());
        return menu;
    }
}
