package com.jason.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.jason.pojo.Result;
import com.jason.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@SuppressWarnings("NullableProblems")
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法运行前运行, 返回true: 放行, 返回false: 不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("preHandle ...");

        // 1. 获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求url: {}", url); //请求路径：http://localhost:8080/login

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        // 这段注释掉, 因为WebConfig配置中已经将"/login"除外
        /*if (url.contains("login")) {
            log.info("登录操作, 放行...");
            return true;
        }*/

        // 3. 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");
        log.info("已从请求头中获取jwt");

        // 4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空, 返回未登录的信息");

            //确保了服务器返回给客户端的响应内容是JSON格式，并且使用UTF-8字符集进行编码。在这个过滤器处理的所有请求上生效。
            response.setContentType("application/json;charset=utf-8");

            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json ---> 阿里巴巴fastJSON---> JSONObject.toJSONString(error);
            response.getWriter().write(JSONObject.toJSONString(error));

            return false;
        }

        // 5. 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("令牌解析失败, 返回未登录的信息");

            //解析失败后将第4步代码直接复制粘贴到这里
            response.setContentType("application/json;charset=utf-8");
            Result error = Result.error("NOT_LOGIN");
            response.getWriter().write(JSONObject.toJSONString(error));

            return false;
        }
        // 6. 放行
        log.info("令牌合法, 放行");
        return true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("postHandle ...");
    }

    @Override // 视图渲染完毕后运行, 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("afterCompletion ...");
    }
}
