package com.ssm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Share {
    private int id;
    private  int cusid;
    private int empid;
    private int sharedEmpid;

    public Share() {
    }
}
