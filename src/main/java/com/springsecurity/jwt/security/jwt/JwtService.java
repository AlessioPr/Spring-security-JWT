package com.springsecurity.jwt.security.jwt;

import com.springsecurity.jwt.security.entity.Role;
import com.springsecurity.jwt.utils.UserConverter;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private long expirationTime;

    public Authentication authentication;

    @PostConstruct
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String generateToken(String userName, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(userName);
        Date date = new Date();
        claims.put("roles", getUserRoles(roles));
        Date validity = new Date(date.getTime() + expirationTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public AbstractAuthenticationToken getAuthentication(String token) {
        List<String> roles = (List<String>) ((JwtParser) new DefaultJwtParser()).setSigningKey(secret).parseClaimsJws(token).getBody().get("roles");
        return new UsernamePasswordAuthenticationToken(getUserName(token), "", UserConverter.getUserRolesFromStringList(roles));
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7, token.length());
        }
        return null;
    }

    public String getUserName(String token) {
        return ((JwtParser) new DefaultJwtParser()).setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = ((JwtParser) new DefaultJwtParser()).setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Token was expired or invalid");
        }
    }

    public List<String> getUserRoles(List<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
    }
}
