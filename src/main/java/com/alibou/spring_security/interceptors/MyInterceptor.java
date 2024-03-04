package com.alibou.spring_security.interceptors;

import com.alibou.spring_security.utils.Global;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



/* Serialization */
public class MyInterceptor implements HandlerInterceptor {
    // dùng de check jwt, auth cac kieu, them vao request truoc khi di vao controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Pre-handle logic");
        try {
            request.setAttribute(Global.URI_REQUEST, request.getRequestURI());
            request.setAttribute(Global.TRACK_LOG_ATTR, "Abc");
        } catch (Exception e) {

        }
        return true; // Nếu trả về false, request sẽ không được tiếp tục xử lý.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // seziable
        System.out.println("response: " + response);
        System.out.println("After completion logic");
    }

}
