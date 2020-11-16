package com.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Customer;
import com.ssm.entity.Employee;
import com.ssm.entity.Visit;
import com.ssm.service.CustomerService;
import com.ssm.service.VisitService;
import com.ssm.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @Autowired
    EmpService empservice;

    @Autowired
    VisitService visitService;


    @RequestMapping("/toShare")
    public String toShare(){
        return "share";
    }


    /**
     *  通过shiro获取当前角色，将其转为employee类型获取id值
     *  通过empid 查询所属的客户信息列表
     * @param page
     * @return
     */
    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll(@RequestParam(value = "page", defaultValue = "1")  int page){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        PageHelper.startPage(page,4);
        List<Customer> Customers = service.selectByEmpId(emp.getEmpid());
        PageInfo pages = new PageInfo(Customers);
        return JSON.toJSONString(pages);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(@RequestParam(value = "cusId") int cusid){
        int delete = service.del(cusid);
        if(delete!=0){return "true";}
        return "false";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Customer cus){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        cus.setEmpid(emp.getEmpid());
        int insert = service.add(cus);
        if(insert!=0){return "true";}
        else return "false";
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(Customer cus){
        int update = service.update(cus);
        if (update>0)return "true";
        return "false";
    }


    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById(@RequestParam(value = "cusId") int id){
        Customer cus = service.selectById(id);
        return JSON.toJSONString(cus);
    }


    /**客户分享
     *
     * 通过传入客户id，员工id 以及收到分享的员工id 进行客户分享
     * 同样使用shiro 获取当前员工的信息
     *
     * @param id
     * @param sharedEmpid
     * @return
     */
    @RequestMapping("/share")
    @ResponseBody
    public String share(@RequestParam(value = "cusId") int id,@RequestParam(value = "sharedEmpid") int sharedEmpid){
        if (service.exitsShare(id,sharedEmpid)==null){
            Subject subject = SecurityUtils.getSubject();
            Employee emp = (Employee) subject.getPrincipal();
            int i = service.share(id, emp.getEmpid(), sharedEmpid);
            if (i > 0) return "true";
        }
        return "false";

    }

    @RequestMapping("/selectAllEmp")
    @ResponseBody
    public String selectAllEmp(){
        List<Employee> Employees = empservice.selectAll(1);
        return JSON.toJSONString(Employees);
    }


    /**当前员工所接受到的客户分享信息
     *
     * @param page
     * @return
     */
    @RequestMapping("/selectShared")
    @ResponseBody
    public String selectShared(@RequestParam(value = "page", defaultValue = "1")  int page){
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        PageHelper.startPage(page,4);
        List<Customer> Customers = service.shareList(emp.getEmpid());
        PageInfo pages = new PageInfo(Customers);
        return JSON.toJSONString(pages);
    }


    @RequestMapping("/transfer")
    @ResponseBody
    public String transfer(@RequestParam(value = "cusId") int cusid,
                           @RequestParam(value = "empid")int empid){
        int transfer = service.transfer(cusid,empid);
        if(transfer>0)return "true";
        return "false";
    }


    @RequestMapping("/OwnVisit")
    @ResponseBody
    public String OwnVisit(@RequestParam(value = "page" ,defaultValue="1") int page){
        PageHelper.startPage(page,4);
        List<Visit> Visits = visitService.selectByEmpId(2);
        PageInfo pages = new PageInfo(Visits);
        return JSON.toJSONString(pages);
    }
}
