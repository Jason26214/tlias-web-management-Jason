package com.jason.filter;

import com.alibaba.fastjson2.JSONObject;
import com.jason.pojo.Result;
import com.jason.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain FilterChain) throws IOException, ServletException {
        // 0. 强转成http的请求/响应对象(因为要使用子类中特有方法)
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 1. 获取请求url
        String url = httpServletRequest.getRequestURL().toString();
        log.info("请求url: {}", url); //请求路径：http://localhost:8080/login

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作, 放行...");
            FilterChain.doFilter(servletRequest, servletResponse); //放行请求
            return; //结束当前方法的执行
        }

        // 3. 获取请求头中的令牌（token）
        String jwt = httpServletRequest.getHeader("token");
        log.info("已经从请求头中获取的令牌");

        // 4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空, 返回未登录的信息");

            //确保了服务器返回给客户端的响应内容是JSON格式，并且使用UTF-8字符集进行编码。在这个过滤器处理的所有请求上生效。
            httpServletResponse.setContentType("application/json;charset=utf-8");

            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json ---> 阿里巴巴fastJSON---> JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(JSONObject.toJSONString(error));

            return;
        }

        // 5. 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("令牌解析失败, 返回未登录的信息");

            //解析失败后将第4步代码直接复制粘贴到这里
            httpServletResponse.setContentType("application/json;charset=utf-8");
            Result error = Result.error("NOT_LOGIN");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(error));

            return;
        }
        // 6. 放行
        log.info("令牌合法, 放行");
        FilterChain.doFilter(servletRequest, servletResponse);
    }
}
