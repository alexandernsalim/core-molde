package com.ta.coremolde.security.filter.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String expired = (String) httpServletRequest.getAttribute("expired");

        if (expired != null) {
            httpServletResponse.getWriter().write("{" +
                "\"code\":\"" + 401 + "\"," +
                "\"message\":\"Token expired\"," +
                "\"data\": \"Token already expired\"" +
            "}");
        } else {
            httpServletResponse.getWriter().write("{" +
                "\"code\":\"" + 401 + "\"," +
                "\"message\":\"Unauthorized\"," +
                "\"data\": \"Token invalid or not provided\"" +
            "}");
        }
    }

}
