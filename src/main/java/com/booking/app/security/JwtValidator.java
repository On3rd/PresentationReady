package com.booking.app.security;


import org.springframework.stereotype.Component;

import com.booking.app.modal.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setEmail(body.getSubject());
            user.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}

