package com.ssm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Customer {
    private  int cusId;
    private String cusName;
    private String address;
    private String contact;
    private String tel;
    private  String email;
    private int empid;
    private  String username;


    public Customer() {}

    public Customer(String cusName, String address, String contact, String tel, String email, int empid, String username) {
        this.cusName = cusName;
        this.address = address;
        this.contact = contact;
        this.tel = tel;
        this.email = email;
        this.empid = empid;
        this.username=username;
    }
}
