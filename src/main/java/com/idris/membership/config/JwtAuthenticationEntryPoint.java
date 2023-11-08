package com.idris.membership.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idris.membership.common.CommonResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String resString = new ObjectMapper().writeValueAsString(new CommonResponse(108, "Token tidak tidak valid atau kadaluwarsa", null));

        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(resString);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.flushBuffer();
    }
}
