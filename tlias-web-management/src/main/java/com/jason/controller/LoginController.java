package com.jason.controller;

import com.jason.pojo.Emp;
import com.jason.pojo.Result;
import com.jason.service.EmpService;
import com.jason.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    private final EmpService empService;

    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    /**
     * 员工登录
     * @param emp emp
     * @return e != null ?
     */
    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录, emp={}", emp);
        Emp e = empService.login(emp);

        //登录成功, 生成令牌, 下发令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }
}
