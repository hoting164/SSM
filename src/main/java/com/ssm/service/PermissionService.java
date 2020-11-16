package com.ssm.service;

import com.ssm.entity.Permission;

import java.util.List;

public interface PermissionService {


    List<Permission> getPermsByRoleId(Integer roleid);

    List<Permission> getMenu(Integer roleid);

    int authorization(Integer roleId,Integer permissionId);

    int delauthorization(Integer roleid,Integer permissionid);

    List<Permission> selecrAll();

    List<Permission> selectById(int roleid );

    List<Integer> selectPermission(Integer roleId);

}
