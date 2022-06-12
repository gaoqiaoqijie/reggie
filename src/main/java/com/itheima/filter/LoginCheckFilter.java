package com.itheima.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.common.MyBaseContext;
import com.itheima.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Classname LoginCheckFilter
 * @Description TODO
 * @Date 2022/5/24 16:18
 * @Created by luochao
 */

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //检查路径
        String [] urls= {
                "/employee/index",
                "/employee/login",
                "/employee/logout",
                "/login/**",
                "/user/**",
                "/backend/**",
                "/front/**"
        };
        //不判断直接放行
        if (checkURL(urls, request.getRequestURI())) {
            log.info("放行: "+request.getRequestURI() );
            filterChain.doFilter(request,response);
            return;
        }
        //设置threadlocal保存id供公共字段填充使用
        HttpSession session = request.getSession();

        /*
        log.info("filter employee id :"+ request.getSession().getAttribute("employee"));
        log.info("filter local :"+ longThreadLocal.get());
*/

        //判断登录状态
        if(session.getAttribute("employee")!=null){
            log.info("放行: "+request.getRequestURI() );
            MyBaseContext.setId((Long) session.getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }
        if(session.getAttribute("user")!=null){
            log.info("放行: "+request.getRequestURI() );
            MyBaseContext.setId((Long) session.getAttribute("user"));
            filterChain.doFilter(request,response);
            return;
        }
        //未登录则向浏览器返回未登录，由前端做页面跳转
        log.info("未登录 "+request.getRequestURI());
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));




    }

    private boolean checkURL(String [] urls,String requestURL){
        for (String url : urls) {
            if(PATH_MATCHER.match(url,requestURL)){
                return true;
            }
        }
        return false;
    }
}
