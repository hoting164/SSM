package com.ssm.service.imp;

import com.ssm.dao.ShareDao;
import com.ssm.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class ShareServiceImp implements ShareService {

    @Autowired
    ShareDao dao;

    @Override
    public List<Integer> selectById(int cusid, int empid) {
        return dao.selectById(cusid, empid);
    }
}
