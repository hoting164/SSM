package com.ssm.dao;

import com.ssm.entity.Visit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VisitDao {

    @Insert("insert into customervisit(id,cusid,empid,content,date) values(#{id},(select cusId from Customer where cusName=#{cusName}),(select empid from emp where username=#{username}),#{content},#{date})")
    int add(Visit v);


    @Delete("delete from customervisit where id=#{id}")
    int del(int id);

    @Update("update customervisit set  content=#{content}, date=#{date} where id=#{id}")
    int update(Visit v);

    @Select("select v.* ,e.username,c.cusName from customervisit v,emp e ,Customer c where v.cusid=c.cusId and e.empid=v.empid")
    List<Visit> selectAll();


    @Select("select v.* ,e.username,c.cusName from customervisit v,emp e ,Customer c where v.cusid=c.cusId and e.empid=v.empid and v.empid=#{empid}")
    List<Visit> selectByEmpId(int empid);

    @Select("select v.* ,e.username,c.cusName from customervisit v,emp e ,Customer c where v.cusid=c.cusId and e.empid=v.empid and v.id=#{id}")
    List<Visit> selectById(int id);
}
