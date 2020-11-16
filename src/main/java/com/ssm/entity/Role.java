package com.ssm.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Role {
    private int roleId;
    private String rolename;
    private String roleinfo;

    public Role() {}

    public Role(int roleId, String rolename, String roleinfo) {
        this.roleId = roleId;
        this.rolename = rolename;
        this.roleinfo = roleinfo;
    }
}
