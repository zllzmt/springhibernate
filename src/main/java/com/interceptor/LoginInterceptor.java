package com.interceptor;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginInterceptor implements HandlerInterceptor {
/*    public static void main(String[] args) {
        String reg="192\\.168\\..*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("192.168.123.12");
        Matcher matcher1 = pattern.matcher("192.167.123.12");
        boolean matches = matcher1.matches();
        System.out.println(matches);
        boolean result = matcher.matches();
        System.out.println(result);

    }*/
    //pre 请求之前
    //在请求到达controller之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reg="192\\.168\\..*";
        //获取客户端的ip
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        //匹配正则表达式   根据reg获取pattern模型
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(remoteAddr);
        boolean result = matcher.matches();
        if (result){
            return  true;
        }else {
            return false;
        }
    }
//在请求到达controller之后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
