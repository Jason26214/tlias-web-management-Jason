package com.jason.mapper;

import com.jason.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

//@SuppressWarnings("all")
@Mapper
public interface EmpMapper {

    /*
      获取总记录数
     */
    //@Select("select count(*) from emp")
    //Long count();

    /*
      当前页列表
     */
    //@Select("select * from emp limit #{start}, #{pageSize}")
    //List<Emp> list(Integer start, Integer pageSize);

    /**
     * 员工信息条件查询(XML)
     * @return Emp列表
     */
    //@Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工(XML)
     * @param ids ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp emp
     */
    @Insert("INSERT INTO emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUES (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id查询员工
     * @param id id
     * @return emp
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 修改员工(XML)
     * @param emp emp
     */
    void update(Emp emp);

    /**
     * 根据username和password查询
     * @param emp emp
     * @return Emp
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除部门下所有员工
     * @param deptId dept_id
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
