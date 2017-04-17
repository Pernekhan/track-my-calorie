package com.trackmycalorie.web.security.jwt;

import com.trackmycalorie.dao.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class TokenAuthenticationService {

    static void addAuthentication(HttpServletResponse res, JwtUserDetails user) {
        res.addHeader(JwtTokenUtils.HEADER_STRING, JwtTokenUtils.generateToken(user));
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenUtils.HEADER_STRING);
        User user = JwtTokenUtils.parseToken(token);
        return user != null ?
                new UsernamePasswordAuthenticationToken(
                        user, null, JwtUserDetails.mapToGrantedAuthorities(user.getRole())) :
                null;
    }
}