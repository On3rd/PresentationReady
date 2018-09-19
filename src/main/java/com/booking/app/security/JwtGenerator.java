package com.booking.app.security;

import org.springframework.stereotype.Component;

import com.booking.app.modal.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {


    public String generate(User user) {


    	
        Claims claims = Jwts.claims()
                .setSubject(user.getEmail());
        claims.put("username", user.getName());
        claims.put("userId", String.valueOf(user.getUser_Id()));
        claims.put("role", user.getRole());
        


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
