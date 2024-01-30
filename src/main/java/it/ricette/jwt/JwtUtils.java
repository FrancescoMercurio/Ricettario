package it.ricette.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.ricette.service.UserDetailsImpl;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("======================BezKoder=Spring===========================")
  private String jwtSecret;

  @Value("604800000") // 7 giorni in millisecondi
  private int jwtExpirationMsForUser;

  @Value("86400000") // 24 ore in millisecondi
  private int jwtExpirationMsForAdmin;

  public String generateJwtToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

	    int expirationMs = userPrincipal.getAuthorities().stream()
	        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
	        ? jwtExpirationMsForAdmin
	        : jwtExpirationMsForUser;

	    return Jwts.builder()
	        .setSubject(userPrincipal.getUsername())
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + expirationMs))
	        .signWith(key(), SignatureAlgorithm.HS256)
	        .compact();
	}
  
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}