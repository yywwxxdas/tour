package com.example.demo.Config;

import com.example.demo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//web配置
@Configuration//注入到ioc容器里
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;//获取拦截器对象
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器，只有登录和注册接口放行
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/traveler/login","/traveler/register");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("file:E:/demovue1/vue-project/src/assets/");
    }

}
