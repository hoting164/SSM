package com.ssm.service.imp;

import com.ssm.dao.RoleDao;
import com.ssm.entity.Role;
import com.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleDao dao;

    @Override
    public List<Role> selectAll() {
        return dao.selectAll();
    }
}
