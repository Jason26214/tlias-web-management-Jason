package com.jason.service.impl;

import com.jason.mapper.DeptLogMapper;
import com.jason.pojo.DeptLog;
import com.jason.service.DeptLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    private final DeptLogMapper deptLogMapper;

    public DeptLogServiceImpl(DeptLogMapper deptLogMapper) {
        this.deptLogMapper = deptLogMapper;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
