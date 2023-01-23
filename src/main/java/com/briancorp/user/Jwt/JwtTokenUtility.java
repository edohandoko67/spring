package com.briancorp.user.Jwt;

import com.briancorp.user.Models.Usere;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtility {

    @Value("${app.jwt.issuer-name}")
    private String ISSUER_NAME;

    @Value("${app.jwt.period-of-validity}")
    private long PERIOD_OF_VALIDITY;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET_KEY;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtility.class);


    public String generateAccessToken(Usere usere) { //setelah login berhasil yang diakses oleh user
        return Jwts.builder()
                .setSubject(usere.getUsername()) //mengambil username dari class userInfo
                .setIssuer(ISSUER_NAME) //mengambil dari app properties (penerbit)
                .setIssuedAt(new Date()) //tanggal pembuatan
                .setExpiration(new Date(System.currentTimeMillis() + PERIOD_OF_VALIDITY)) //tanggal ekspired
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        if (parseClaims(accessToken) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername(String accessToken) {
        return getSubject(accessToken);
    }

    String getSubject(String accessToken) {
        return parseClaims(accessToken).getBody().getSubject(); //mengambil subject (payload)
    }

    private Jws<Claims> parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return null;
    }
}
