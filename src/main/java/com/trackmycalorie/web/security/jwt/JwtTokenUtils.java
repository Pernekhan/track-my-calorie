package com.trackmycalorie.web.security.jwt;

import com.trackmycalorie.dao.entity.Role;
import com.trackmycalorie.dao.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

public class JwtTokenUtils {
    public static final String HEADER_STRING = "Authorization";
    private static final String SECRET = "TheSecretGarden";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String CLAIM_KEY_USER_ID = "id";
    private static final String CLAIM_KEY_USER_ROLE = "role";

    public static String generateToken(JwtUserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim(CLAIM_KEY_USER_ID, user.getId())
                .claim(CLAIM_KEY_USER_ROLE, user.getRole())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static User parseToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        return parseToken(token);
    }

    public static User parseToken(String token) {
        if (token == null) return null;
        User user = new User();
        Claims claims = getClaimsFromToken(token);
        user.setUsername(claims.getSubject());
        user.setId(((Integer) claims.get(CLAIM_KEY_USER_ID)).longValue());
        user.setRole(Role.valueOf((String) claims.get(CLAIM_KEY_USER_ROLE)));
        return user;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
