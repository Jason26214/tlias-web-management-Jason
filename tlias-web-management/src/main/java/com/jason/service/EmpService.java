package com.jason.service;

import com.jason.pojo.Emp;
import com.jason.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService {

    /**
     * 条件分页查询
     *
     * @param page      页码
     * @param pageSize  每页记录数
     * @param name      姓名
     * @param gender    性别
     * @param begin     查询开始时间
     * @param end       查询结束时间
     * @return          PageBean
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工
     * @param ids 员工id数组
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp emp
     */
    void save(Emp emp);

    /**
     * 根据id查询员工
     * @param id id
     * @return emp
     */
    Emp getById(Integer id);

    /**
     * 修改员工
     * @param emp emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp emp
     * @return e != null ?
     */
    Emp login(Emp emp);
}
