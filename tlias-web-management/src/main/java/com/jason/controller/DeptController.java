package com.jason.controller;

import com.jason.anno.Log;
import com.jason.pojo.Dept;
import com.jason.pojo.Result;
import com.jason.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 部门管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    //加了lombok提供的@Slf4j注解, 不用写这句了
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 查询部门数据
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
        //在postman中发出请求, 这边(服务端)接到请求, 因为加了注释@RestController中包含@ResponseBody, 即将"Result"对象自动以json格式返回
    }

    /**
     * 删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept dept
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        //调用service新增部门
        deptService.add(dept);
    return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     * @param dept dept
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
