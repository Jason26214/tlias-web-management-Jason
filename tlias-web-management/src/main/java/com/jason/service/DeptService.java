package com.jason.service;

import com.jason.pojo.Dept;

import java.util.List;

//部门业务规则
public interface DeptService {

    /**
     * 查询全部部门数据
     * @return List<Dept>
     */
    List<Dept> list();

    /**
     * 删除部门, 还要删除部门下的所有员工
     * @param id id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept dept
     */
    void add(Dept dept);

    /**
     * 根据ID查询部门
     * @return Dept
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept dept
     */
    void update(Dept dept);
}
