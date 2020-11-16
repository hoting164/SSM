package com.ssm.dao;

import com.ssm.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PerimssionDao {


    @Select(" select roleid, p.*  from permission p, rolepermission rp  where p.permissionId=rp.permissionId and  roleid=#{roleid}")
    List<Permission> getPermsByRoleId(Integer roleid);

    @Select("select p.*  from permission p, rolepermission rp  where p.permissionId=rp.permissionId and  rp.roleid=#{roleid}  and ismenu=1 order by permissionId")
    List<Permission> getMenu(Integer roleid);

    @Select("select * from permission where pinfo is not null")
    List<Permission> selecrAll();


    @Insert("insert into rolepermission(roleId,permissionId) values(#{arg0},#{arg1})")
    int authorization(Integer roleId,Integer permissionId);

    @Delete("delete from rolepermission where roleId=#{arg0} and permissionId=#{arg1}")
    int delauthorization(Integer roleId,Integer permissionId);


    @Select("select p.* from permission p ,rolepermission rp where p.permissionId=rp.permissionId and rp.roleId=#{roleId} and p.pinfo is not null")
    List<Permission> selectById(int roleId );


    @Select("select rp.permissionId from permission p  ,rolepermission rp  where p.permissionId=rp.permissionId  and rp.roleid =#{roleId} ")
    List<Integer> selectPermission(Integer roleId);

}
