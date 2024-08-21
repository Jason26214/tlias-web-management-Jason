package com.jason.service.impl;

import com.jason.mapper.DeptMapper;
import com.jason.mapper.EmpMapper;
import com.jason.pojo.Dept;
import com.jason.pojo.DeptLog;
import com.jason.service.DeptLogService;
import com.jason.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptLogService deptLogService;
    private final DeptMapper deptMapper;
    private final EmpMapper empMapper;

    public DeptServiceImpl(DeptMapper deptMapper, EmpMapper empMapper, DeptLogService deptLogService) {
        this.deptMapper = deptMapper;
        this.empMapper = empMapper;
        this.deptLogService = deptLogService;
    }

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    /*
     * 根据部门id, 删除部门信息及部下的所有员工
     * @Transactional spring事务管理
     * 默认情况下，只有出现RuntimeException(运行时异常)才会回滚事务。
     * 假如想让所有的异常都回滚，需要来配置rollbackFor属性指定异常类型
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id); //根据部门id删除部门信息

            //错误测试(RuntimeException)
            //int i = 1 / 0;
            //错误测试(RuntimeException以外,测试这类测试要手动写代码一直往上抛,可烦了)
            /*if (true) {
                throw new Exception("出现异常");
            }*/

            empMapper.deleteByDeptId(id); //删除部门下的所有员工信息
        } finally {
            //记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作, 此次解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        //补全部门数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用持久层增加功能
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
