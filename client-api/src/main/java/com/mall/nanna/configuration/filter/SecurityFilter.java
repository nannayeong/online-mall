package com.mall.nanna.configuration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * 보안 필터
 * @author EDA
 * @since 2022-12-12
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("[SecurityFilter] …");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("Authorization");
        boolean hasToken = Objects.isNull(authToken);

        log.info(">> hasToken: {}, authToken: {}", hasToken, authToken);

//        if (!hasToken) {
//            log.info(">> authToken: {}", authToken);
//            LoginD token = TokenUtils.decodeJwtToken(authToken);
//            request.setAttribute("token", token);
//        }

        chain.doFilter(request, response);
    }
}
