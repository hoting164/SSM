package com.ssm.dao;

import com.ssm.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("select * from Role where roleId<3")
    List<Role> selectAll();
}
