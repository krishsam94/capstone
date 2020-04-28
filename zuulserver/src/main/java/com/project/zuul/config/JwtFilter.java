package com.project.zuul.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class JwtFilter extends GenericFilterBean {

    private static String jwtKey;

    @Value("${spring.jwt.key}")
    public void sertJwtKey(String key) {
        jwtKey = key;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("authorization");

        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(request, response);
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                throw new ServletException("Missing or Invalid Authentication Header");
            }

            String jwtToken = authHeader.substring(7);

            Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(jwtToken).getBody();
            httpRequest.setAttribute("username", claims);
            chain.doFilter(request, response);
        }

    }

}
