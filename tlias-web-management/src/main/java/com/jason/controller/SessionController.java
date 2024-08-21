package com.jason.controller;

import com.jason.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cookie , HttpSession演示
 */
@Slf4j
@RestController
public class SessionController {
    /*
     * cookie 是客户端会话跟踪技术，它是存储在客户端浏览器的
     * 优点：HTTP协议中支持的技术
     * 缺点：
     *  - 移动端APP无法使用Cookie
     *  - 不安全，用户可以自己禁用Cookie
     *  - Cookie不能跨域(跨域区分三个维度：协议、IP/域名、端口, 一个对不上就算跨域. 例如前后端分离部署, ip地址和端口都不同就算跨域了)
     */
    //设置Cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        response.addCookie(new Cookie("login_username", "Jerry")); //设置Cookie/响应Cookie
        return Result.success();
    }

    //获取Cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); //获取所有Cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_username")) {    //输出name为login_username的cookie
                System.out.println("login_username: " + cookie.getValue());
            }
        }
        return Result.success();
    }

    /*
     * Session，它是服务器端会话跟踪技术，所以它是存储在服务器端的
     * 优点：存储在服务端，安全
     * 缺点：
     *  - 服务器集群环境下无法直接使用Session
     *  - Cookie的缺点
     */
    // 往HttpSession中存储数据
    @GetMapping("/s1")
    public Result session1(HttpSession session) {
        log.info("HttpSession-s1: {}", session.hashCode());

        session.setAttribute("loginUser", "tom"); //往session中存储数据
        return Result.success();
    }

    // 从HttpSession中获取数据
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("HttpSession-s2: {}", session.hashCode());

        Object loginUser = session.getAttribute("loginUser"); //从session中获取数据
        log.info("loginUser: {}", loginUser);
        return Result.success(loginUser);
    }
}

