package com.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 10388 on 2017/6/3.
 */
@WebServlet(name = "legalAccess")
public class legalAccess extends HttpServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getSession().getAttribute("login") == null){
            request.setAttribute("error", "noUser");
            request.getRequestDispatcher("controlServlet.do").forward(request,response);
        }else if(request.getSession().getAttribute("login").equals("on")){
            filterChain.doFilter(request, response);
        }else {
            request.setAttribute("error", "noUser");
            request.getRequestDispatcher("controlServlet.do").forward(request,response);
        }
    }
}
