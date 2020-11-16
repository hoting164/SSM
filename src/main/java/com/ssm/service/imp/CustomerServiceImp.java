package com.ssm.service.imp;

import com.ssm.dao.CustomerDao;
import com.ssm.entity.Customer;
import com.ssm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerDao dao;
    @Override
    public List<Customer> selectAll() {
        return dao.selectAll();
    }

    @Override
    public int add(Customer customer) {
        return dao.add(customer);
    }

    @Override
    public int update(Customer customer) {
        return dao.update(customer);
    }

    @Override
    public int del(int cusid) {
        return dao.del(cusid);
    }

    @Override
    public List<Customer> selectByEmpId(int id) {
        return dao.selectByEmpId(id);
    }

    @Override
    public Customer selectById(int id) {
        return dao.selectById(id);
    }


    @Override
    public int transfer(int cusid, int empid) {
        return dao.transfer(cusid,empid);
    }

    @Override
    public int share(int cusid, int empid,int sharedEmpid) {
        return dao.share(cusid, empid, sharedEmpid);
    }

    @Override
    public List<Customer> shareList(int id) {
        return dao.shareList(id);
    }

    @Override
    public Customer exitsShare(int cusid, int sharedEmpid) {
        return dao.exitsShare(cusid, sharedEmpid);
    }

    @Override
    public Customer selectByCusName(String name) {
        return dao.selectByCusName(name);
    }
}
