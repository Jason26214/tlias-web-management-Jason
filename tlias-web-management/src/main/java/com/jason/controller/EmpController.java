package com.jason.controller;

import com.jason.anno.Log;
import com.jason.pojo.Emp;
import com.jason.pojo.PageBean;
import com.jason.pojo.Result;
import com.jason.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    //@构造函数依赖注入
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    /**
     * 条件分页查询
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询: page-{}; pageSize-{}; name-{}; gender-{}; begin-{}; end-{}",
                page, pageSize, name, gender, begin, end);

        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);

        return Result.success(pageBean);
    }

    /**
     * 批量删除员工(包括一个)
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工的ids: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     * @param emp emp
     * @return null
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工, emp: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id id
     * @return emp
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息, id: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     * @param emp emp
     * @return null
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工, emp: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}




