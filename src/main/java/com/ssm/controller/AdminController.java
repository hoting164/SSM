package com.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Permission;
import com.ssm.entity.Employee;
import com.ssm.entity.Role;
import com.ssm.service.PermissionService;
import com.ssm.service.EmpService;
import com.ssm.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EmpService empService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permission;

    @RequiresPermissions("admin:edit")
    @RequestMapping("/toRight")
    public String toRight(){return  "rightsManagement";}


    /**
     *  更改员工角色
     * @param roleid 传入角色id
     * @param empid  传入员工id
     * @return
     */

    @RequiresPermissions("admin:updateRole")
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(@RequestParam(value = "roleId") int roleid,
                             @RequestParam(value = "empid") int empid){
        int i = empService.updateRole(roleid, empid);
        if (i>0)return "true";
        return "false";
    }


    // 列出角色
    @RequestMapping("/role")
    @ResponseBody
    public String role(){
        List<Role> Roles = roleService.selectAll();
        return JSON.toJSONString(Roles);
    }

    @RequestMapping("/ListAll")
    @ResponseBody
    public String ListAll(@RequestParam(value = "page" ,defaultValue = "1") int page){
        PageHelper.startPage(page,4);
        List<Employee> Employees = empService.selectAll(2);
        PageInfo pages = new PageInfo(Employees);
        return JSON.toJSONString(pages);
    }


    /**
     *传入员工id数组进行遍历，重置密码
     * @param empid
     * @return
     */
    @RequiresPermissions("admin:resetPassword")
    @RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(int[] empid){
        for (int i : empid) {
            int j = empService.resetPassword(i);
            if (j==0) return "false";
        }
        return "true";
    }


    @RequestMapping("/listAuthorization")
    @ResponseBody
    public String listAuthorization(){
        List<Permission> authorization = permission.selecrAll();
        return JSON.toJSONString(authorization);
    }


    @RequiresPermissions("admin:authorization")
    @RequestMapping("/authorization")
    @ResponseBody
    public String authorization(@RequestParam(value = "roleId") int roleid ,@RequestParam(value = "permissionId") int permissionid){
        List<Integer> list = permission.selectPermission(roleid);
        if (list.contains(permissionid)) return "false";
        int authorization = permission.authorization(roleid, permissionid);
        if (authorization>0) return "true";
        return "false";
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById( int roleid){
        List<Permission> permissions = permission.selectById(roleid);
        return JSON.toJSONString(permissions);
    }


    @RequiresPermissions("admin:authorization")
    @RequestMapping("/delAuthorization")
    @ResponseBody
    public String delAuthorization(@RequestParam(value = "roleId") int roleid ,@RequestParam(value = "permissionId") int permissionid){
        int authorization = permission.delauthorization(roleid, permissionid);
        if (authorization>0) return "true";
        return "false";
    }
}
