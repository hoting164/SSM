package com.ssm.service.imp;

import com.ssm.dao.EmpDao;
import com.ssm.entity.Employee;
import com.ssm.service.EmpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("empService")
public class EmpServiceImp implements EmpService {

    @Resource
    EmpDao empDao;

    public List<Employee> selectAll(Integer roleid) {
        return empDao.selectAll(roleid);
    }


    public int insert(Employee emp) {
        return empDao.Add(emp);
    }

    public int update(Employee emp) {
        return empDao.update(emp);
    }

    @Override
    public int updateRole(Integer roleid,Integer empid) {
        return empDao.updateRole(roleid,empid);
    }

    public int delete(Integer id) {
        return empDao.del(id);
    }

    @Override
    public List<Employee> SelectById(Integer empid) {
        return empDao.SelectById(empid);
    }

    @Override
    public Employee SelectByUserName(String username) {
        return empDao.SelectByUserName(username);
    }

    @Override
    public Employee SelectByName(String username) {
        return empDao.SelectByName(username);
    }

    public int updatePassword(int empid,String password){
        return empDao.updatePassword(empid,password);
    }

    @Override
    public int resetPassword(int empid) {
        return empDao.resetPassword(empid);
    }
}
 