package com.ssm.service;

import com.ssm.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAll();

    int add(Customer customer);

    int update(Customer customer);

    int del(int cusid);

    List<Customer> selectByEmpId(int id);

    Customer selectById(int id);

    int transfer(int cusid,int empid);

    int share(int cusid, int empid,int sharedEmpid);

    List<Customer> shareList(int id );

    Customer exitsShare(int cusid, int sharedEmpid);

    Customer selectByCusName(String name);
}
