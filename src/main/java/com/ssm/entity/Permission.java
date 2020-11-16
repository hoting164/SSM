package com.ssm.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Permission {
    private String permissionId;
    private String pname;
    private String purl;
    private String pinfo;
}
