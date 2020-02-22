package com.ta.coremolde.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.coremolde.config.JwtConfig;
import com.ta.coremolde.model.request.AuthenticationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtConfig jwtConfig;
    private AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;

        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest credentials = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(), credentials.getPassword(), Collections.emptyList());

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        Long now = System.currentTimeMillis();
        List<String> authorities = authentication.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecretKey().getBytes())
                .compact();

        try {
            response.getWriter().write("{" +
                "\"code\":\"" + 200 + "\"," +
                "\"message\":\"Success\"," +
                "\"data\": {" +
                    "\"token\":\"" + jwtConfig.getPrefix() + token + "\"," +
                "}" +
            "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        try {
            response.getWriter().write("{" +
                "\"code\":\"" + 401 + "\"," +
                "\"message\":\"Unauthorized\"," +
                "\"data\": {}" +
            "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
