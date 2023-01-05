package pl.edu.pw.elka.pis05.prorec.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import pl.edu.pw.elka.pis05.prorec.security.model.User;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final String jwtSecret;
    private final int jwtExpirationMs;

    public JwtUtils(@Value("${prorec.app.jwtSecret}") final String jwtSecret,
            @Value("${prorec.app.jwtExpirationMs}") final int jwtExpirationMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateJwtToken(final Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(final String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (final SignatureException e) {
            logger.error(String.format("Invalid JWT signature: %s", e.getMessage()));
        } catch (final MalformedJwtException e) {
            logger.error(String.format("Invalid JWT token: %s", e.getMessage()));
        } catch (final ExpiredJwtException e) {
            logger.error(String.format("JWT token expired: %s", e.getMessage()));
        } catch (final UnsupportedJwtException e) {
            logger.error(String.format("JWT token not supported: %s", e.getMessage()));
        } catch (final IllegalArgumentException e) {
            logger.error(String.format("JWT claims string is empty: %s", e.getMessage()));
        }

        return false;
    }
}
