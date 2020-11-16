package com.ssm.service;


import com.ssm.entity.Employee;

import java.util.List;


public interface EmpService {
    List<Employee> selectAll(Integer roleid);


    int insert(Employee emp);

    int update(Employee emp);

    int updateRole(Integer roleid,Integer empid);

    int delete(Integer id);

    List<Employee> SelectById(Integer empid);

    Employee SelectByUserName(String username);

    Employee SelectByName(String username);

    int updatePassword(int empid,String password);

    int resetPassword(int empid);


}
