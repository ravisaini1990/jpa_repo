package com.ravi.jpa_repo.security.custom_filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class CustomSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        System.out.println("Before Any request :: "+url);
        filterChain.doFilter(request, response);

        System.out.println("After Any request");
    }
}
