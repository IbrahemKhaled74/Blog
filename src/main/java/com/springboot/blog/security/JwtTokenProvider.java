package com.springboot.blog.security;

import com.springboot.blog.exception.BlogException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;


@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String secretKey;
    @Value("${app-jwt-expiration-milliseconds}")
    private long expiration;
    //to generate the token
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date current=new Date();
        Date expirationDate=new Date(current.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(current)
                .expiration(expirationDate)
                .signWith(key())
                .compact();

    }
    //to get the userName from token
    public String getUsername(String token){
       return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    //check is token valid or not
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw new BlogException(HttpStatus.BAD_REQUEST,"Invalid JWT Token");
        }catch (ExpiredJwtException expiredJwtException){
            throw new BlogException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        }catch (UnsupportedJwtException unsupportedJwtException){
            throw new BlogException(HttpStatus.BAD_REQUEST,"Unsupported JWT Token");
        }catch (IllegalArgumentException illegalArgumentException){
            throw new BlogException(HttpStatus.BAD_REQUEST,"JWT Token is null or empty");
        }
    }
    private Key key(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

}
