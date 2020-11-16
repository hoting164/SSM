package com.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShareDao {

    @Select("select sharedEmpid from customershare where cusid=#{arg0} and empid=#{arg1}")
    List<Integer> selectById(int cusid, int empid);
}
