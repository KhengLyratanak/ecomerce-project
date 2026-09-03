package com.ecommerce.project.common.filter;

import com.ecommerce.project.common.constants.RequestConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        try {
            String requestId = UUID.randomUUID().toString();

            MDC.put(RequestConstants.REQUEST_ID,requestId);

            String httpMethod = httpRequest.getMethod();

            String requestUri = httpRequest.getRequestURI();
            String requestQuery = httpRequest.getQueryString();
            String fullRequestPath = requestQuery != null?
                    requestUri.concat("?").concat(requestQuery) : requestUri;

            MDC.put(RequestConstants.HTTP_METHOD,httpMethod);
            MDC.put(RequestConstants.REQUEST_PATH,fullRequestPath);
            //execute our implementation
            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            MDC.clear();
        }
    }

}
