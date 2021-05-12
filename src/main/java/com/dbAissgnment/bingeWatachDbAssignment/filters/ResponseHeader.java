package com.dbAissgnment.bingeWatachDbAssignment.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/filter-response-header/*")
@Component
public class ResponseHeader implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        long creationTime = httpServletRequest.getSession().getCreationTime();
        long now = System.currentTimeMillis();
        httpServletResponse.setHeader(
                "ExecutionTime",String.valueOf(now - creationTime) + "ms");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
