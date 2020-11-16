package com.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Customer;
import com.ssm.entity.Employee;
import com.ssm.service.CustomerService;
import com.ssm.service.VisitService;
import com.ssm.service.EmpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
//@RequiresRoles("manager")
@RequestMapping("/Manager")
public class ManagerController {

    @Autowired
    EmpService empService;

    @Autowired
    CustomerService customerService;

    @Autowired
    VisitService visitService;

    @RequestMapping("/error")
    public  String toError(){return "error";}

    @RequiresPermissions("emp:edit")
    @RequestMapping("/empManage")
    public String empManage(){
        return "empManage";
    }

    @RequiresPermissions("Customer:edit")
    @RequestMapping("/cusManage")
    public String CusManage(){
        return "customerManage";
    }

    @RequestMapping("/SelectAllEmp")
    @ResponseBody
    public  String SelectAllEmp(@RequestParam(value = "page",defaultValue ="1" ) int page, HttpServletRequest request){
        Employee emp1 = (Employee) request.getSession().getAttribute("emp");
        Integer roleid = empService.SelectByUserName(emp1.getUsername()).getRoleid();
        PageHelper.startPage(page,4);
        List<Employee> emp = empService.selectAll(roleid);
        PageInfo pages = new PageInfo(emp);
        return JSON.toJSONString(pages);
    }

    @RequiresPermissions("emp:add")
    @RequestMapping("/addEmp")
    @ResponseBody
    public String addEmp(Employee emp){
        if(empService.SelectByName(emp.getUsername())!=null){return "false";}
        emp.setRoleid(1);
        int insert = empService.insert(emp);
        if(insert!=0){return "true";}
        else return "false";
    }

    @RequiresPermissions("emp:update")
    @RequestMapping("/updateEmp")
    @ResponseBody
    public String updateEmp(Employee emp){
        int update = empService.update(emp);
        if (update>0)return "true";
        return "false";
    }

    @RequiresPermissions("emp:del")
    @RequestMapping("/delEmp")
    @ResponseBody
    public String delEmp(@RequestParam(value = "empid") int empid){
        if (customerService.selectByEmpId(empid).size()!=0){return "false";}
        int delete = empService.delete(empid);
        if(delete>0){return "true";}
        else return "false";
    }

    @RequestMapping("/SelectAllCustomer")
    @ResponseBody
    public String SelectAllCustomer(@RequestParam(value = "page",defaultValue ="1" ) int page){
        PageHelper.startPage(page,4);
        List<Customer> Customers = customerService.selectAll();
        PageInfo pages = new PageInfo(Customers);
        return JSON.toJSONString(pages);
    }

    @RequiresPermissions("Customer:add")
    @RequestMapping("/addCustomer")
    @ResponseBody
    public String addCustomer(Customer cus){
        Integer empid = empService.SelectByName(cus.getUsername()).getEmpid();
        cus.setEmpid(empid);
        int insert = customerService.add(cus);
        if(insert!=0){return "true";}
        else return "false";
    }

    @RequiresPermissions("Customer:update")
    @RequestMapping("/updateCustomer")
    @ResponseBody
    public String updateCustomer(Customer cus){
        int update = customerService.update(cus);
        if (update>0)return "true";
        return "false";
    }


    @RequiresPermissions("Customer:del")
    @RequestMapping("/delCustomer")
    @ResponseBody
    public String delCustomer(@RequestParam(value = "cusId") int cusid){
        int delete = customerService.del(cusid);
        if(delete!=0){return "true";}
        return "false";
    }


    @RequiresPermissions("Customer:transfer")
    @RequestMapping("/transferCustomer")
    @ResponseBody
    public String transferCustomer(@RequestParam(value = "cusId") int cusid,
                                   @RequestParam(value = "empid") int empid){
        int transfer = customerService.transfer(cusid,empid);
        if(transfer>0)return "true";
        return "false";
    }
}
