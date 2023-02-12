package com.mall.nanna.configuration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * cors 필터
 * @author EDA
 * @since 2022-12-12
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    final String[] ACCEPTED_ORIGIN = {
            "http://192.168.5.56:8080", // lees-mac
            "http://192.168.5.23:8080", // ay-mac
            "http://192.168.5.69:8080", // ge-com
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("[CorsFilter] …");

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        final String ORIGIN = httpRequest.getHeader("origin");
        String headerOrigin = "";

        if (Arrays.stream(ACCEPTED_ORIGIN)
                .filter(ao -> ao.equals(ORIGIN))
                .findFirst()
                .isPresent()
        ) {
            log.info(">> 허가된 origin: {}", ORIGIN);
            headerOrigin = ORIGIN;
        } else {
            log.info(">> 허가되지 않은 origin: {}", ORIGIN);
        }

        httpResponse.setHeader("Access-Control-Allow-Origin", headerOrigin);
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods","*");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Authorization, temp");

        chain.doFilter(request, response);
    }
}
