package com.ssm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Employee implements Serializable {
    private Integer empid;
    private String username;
    private String password;
    private  String tel;
    private String name;
    private  String email;
    private  Integer roleid;
    private String roleinfo;

    public Employee(){}

    public Employee(String username, String password, String tel, String name, String email) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.email = email;
    }

    public Employee(Integer empid, String username) {
        this.empid = empid;
        this.username = username;
    }
}
