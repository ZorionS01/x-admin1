package com.szw.config;

import com.szw.common.utils.JwtUtil;
import com.szw.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Szw 2001
 * @Date 2023/5/3 18:39
 * @Slogn 致未来的你！
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);
        registration.addPathPatterns("/**").excludePathPatterns("/user/login",
                "/user/info",
                "/user/logout",
                "/error",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/**");
    }
}
