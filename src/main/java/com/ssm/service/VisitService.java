package com.ssm.service;

import com.ssm.entity.Visit;

import java.util.List;

public interface VisitService {
    int add(Visit v);

    int del(int id);

    int update(Visit v);

    List<Visit> selectAll();

    List<Visit> selectByEmpId(int empid);

    List<Visit> selectById(int empid);
}
