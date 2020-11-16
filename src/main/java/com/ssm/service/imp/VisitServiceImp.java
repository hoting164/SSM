package com.ssm.service.imp;

import com.ssm.dao.VisitDao;
import com.ssm.entity.Visit;
import com.ssm.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class VisitServiceImp implements VisitService {


    @Autowired
    VisitDao dao;

    @Override
    public int add(Visit v) {
        return dao.add(v);
    }

    @Override
    public int del(int id) {
        return dao.del(id);
    }

    @Override
    public int update(Visit v) {
        return dao.update(v);
    }

    @Override
    public List<Visit> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Visit> selectByEmpId(int empid) {
        return dao.selectByEmpId(empid);
    }

    @Override
    public List<Visit> selectById(int id) {
        return dao.selectById(id);
    }
}
