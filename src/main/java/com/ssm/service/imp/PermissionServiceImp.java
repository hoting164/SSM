package com.ssm.service.imp;

import com.ssm.dao.PerimssionDao;
import com.ssm.entity.Permission;
import com.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class PermissionServiceImp implements PermissionService {


    @Autowired
    PerimssionDao dao;


    @Override
    public List<Permission> getPermsByRoleId(Integer roleid) {
        return dao.getPermsByRoleId(roleid);
    }

    @Override
    public List<Permission> getMenu(Integer roleid) {
        return dao.getMenu(roleid);
    }

    @Override
    public int authorization(Integer roleId, Integer permissionId) {
        return dao.authorization(roleId, permissionId);
    }

    @Override
    public int delauthorization(Integer roleid, Integer permissionid) {
        return dao.delauthorization(roleid, permissionid);
    }

    @Override
    public List<Permission> selecrAll() {
        return dao.selecrAll();
    }

    @Override
    public List<Permission> selectById(int roleid) {
        return dao.selectById(roleid);
    }

    @Override
    public List<Integer> selectPermission(Integer roleId) {
        return dao.selectPermission(roleId);
    }
}
