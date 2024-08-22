package com.example.demo.interceptors;

import com.example.demo.until.JwtUtil;
import com.example.demo.until.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
//定义拦截器
@Component//将拦截器对象注入到ioc容器里
public class LoginInterceptor implements HandlerInterceptor {
    @Override//拦截要执行操作的用户
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");//通过请求头获得token
        //验证token
        try {
            Map<String,Object> claims= JwtUtil.parseToken(token);//解析获得的token
            //把业务数据存到ThreadLocalUtil中
            ThreadLocalUtil.set(claims);
            return true;
        }catch (Exception e){
            response.setStatus(401);//验证失败
            return false;
        }
    }
}
