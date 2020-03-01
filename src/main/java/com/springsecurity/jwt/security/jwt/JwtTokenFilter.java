package com.springsecurity.jwt.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String token = jwtService.resolveToken((HttpServletRequest) request);
        if (token != null && jwtService.validateToken(token)) {
            AbstractAuthenticationToken authentication = jwtService.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        chain.doFilter(request, response);
    }
}
