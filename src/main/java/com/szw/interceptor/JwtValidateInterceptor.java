package com.szw.interceptor;

import com.alibaba.fastjson2.JSON;
import com.szw.common.utils.JwtUtil;
import com.szw.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Szw 2001
 * @Date 2023/5/3 18:34
 * @Slogn 致未来的你！
 */
@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        log.debug(request.getRequestURI()+"正在验证："+token);
        if (token != null){
            try {
                jwtUtil.parseToken(token);
                log.debug(request.getRequestURI()+"验证通过");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug(request.getRequestURI()+"验证不通过，禁止访问");
        response.setContentType("application/json;charset=utf-8");
        Result<Object> fail = Result.fail(20003, "jwt无效,请重新登录");
        response.getWriter().write(JSON.toJSONString(fail));
        return false;
    }
}
