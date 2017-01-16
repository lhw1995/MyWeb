package com.restaurant.filter;

import com.restaurant.entity.Consumer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lhw on 2016/12/11.
 */
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Consumer consumer = (Consumer)request.getSession().getAttribute("loginUser");
        if(consumer == null) {
            request.setAttribute("message", "必须先登录才能完成操作。");
            request.getRequestDispatcher("/restaurant/message").forward(request, response);
        }

        filterChain.doFilter(request, response);
    }
}
