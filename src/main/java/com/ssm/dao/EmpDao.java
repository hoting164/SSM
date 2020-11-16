package com.ssm.dao;

import com.ssm.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmpDao {

    @Select("select * from emp where roleid<=#{roleid} order by empid")
    List<Employee> selectAll(Integer roleid);

    @Insert("insert into emp(username,password,tel,email,roleid) values(#{username},#{password},#{tel},#{email},1)")
    int Add(Employee emp);

    @Update("update emp set username=#{username},password=#{password},tel=#{tel},email=#{email} where empid=#{empid} ")
    int update(Employee emp);

    @Update("update emp set roleid=#{arg0} where empid=#{arg1}")
    int updateRole(Integer roleid,Integer empid);

    @Delete("delete from emp where empid = #{empid}")
    int del(int id);

    @Select("select * from emp where username=#{username}")
    Employee SelectByUserName(String username);

    @Select("select emp.*,r.roleInfo from emp ,Role r  where 1=1  and emp.roleid=r.roleId  and  username=#{username}")
    Employee SelectByName(String username);

    @Select("select * from emp where empid=#{empid}")
    List<Employee> SelectById(Integer empid);

    @Update("update emp set password=#{arg1} where empid=#{arg0}")
    int updatePassword(int empid ,String password);

    @Update("update emp set password=123456 where empid=#{empid}")
    int resetPassword(int empid);
}
