package com.miumg.wstickets.config.filters;

import com.miumg.wstickets.config.LogsConfiguration;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author wilver
 */
public class FiltroLog implements Filter {

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogsConfiguration.addKey();
        try {
            chain.doFilter(request, response);
        } finally {
            LogsConfiguration.removeKey();
        }
    }
    
    @Override
    public void destroy() {
    }

}
