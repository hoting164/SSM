package com.ssm.dao;

import com.ssm.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CustomerDao {

    @Select("select c.*  , e.username from Customer c, emp e  where c.empid=e.empid")
    List<Customer> selectAll();

    @Insert("insert into Customer(cusName,address,contact,tel,email,empid) values(#{cusName},#{address},#{contact},#{tel},#{email},#{empid})")
    int add(Customer customer);

    @Update("update Customer set cusName=#{cusName},address=#{address},contact=#{contact},tel=#{tel},email=#{email} where cusId=#{cusId}")
    int update(Customer customer);

    @Delete("delete from Customer where cusId=#{cusId}")
    int del(int cusid);

    @Select("select * from Customer where empid=#{empid}")
    List<Customer> selectByEmpId(int empid);

    @Select("select Customer.*,emp.username from Customer,emp where Customer.empid=emp.empid and cusId=#{cusId}")
    Customer selectById(int id);

    @Update("update Customer set empid=#{arg1} where cusId=#{arg0}")
    int transfer(int cusid,int empid);

    @Insert("insert into customershare(cusid,empid,sharedEmpid) values(#{arg0},#{arg1},#{arg2})")
    int share(int cusid, int empid,int sharedEmpid);

    @Select("select Customer.* ,e.username from Customer join emp e on Customer.empid = e.empid join customershare c on Customer.cusId = c.cusid  and c.sharedEmpid=#{sharedEmpid} ")
    List<Customer> shareList(int sharedEmpid);

    @Select("select c.* from Customer c,customershare cs where c.cusId=cs.cusid and cs.cusid=#{arg0} and sharedEmpid=#{arg1}")
    Customer exitsShare(int cusid , int sharedEmpid);


    @Select("select Customer.* ,emp.username from Customer,emp where emp.empid=Customer.empid and cusName=#{cusName}")
    Customer selectByCusName(String name);
}
