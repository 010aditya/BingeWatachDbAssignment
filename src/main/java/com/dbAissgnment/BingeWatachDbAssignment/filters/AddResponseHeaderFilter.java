package com.dbAissgnment.BingeWatachDbAssignment.filters;

import com.dbAissgnment.BingeWatachDbAssignment.aspectj.ExecutionTimeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/filter-response-header/*")
public class AddResponseHeaderFilter implements Filter {

    @Autowired
    ExecutionTimeAdvice executionTimeAdvice;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(
                "ExecutionTime", "Execution time in logs");
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
