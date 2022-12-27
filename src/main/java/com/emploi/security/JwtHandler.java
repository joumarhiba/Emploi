package com.emploi.security;

import com.emploi.model.Admin;
import com.emploi.model.Company;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHandler {

    Map<String, Object> claims = new HashMap<>();
    private String SECRET_KEY = "SPRING_AUTH_JWT_SECRET";

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token) {
        return extractClaim(token, (claims) -> (String)claims.get("role"));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void setCustomClaim(String key, Object value){
        claims.put(key, value);
    }

    public String generateToken(Admin userDetails) {
        claims.put("role", userDetails.getUserRole().toString());
        return createToken(claims, userDetails.getEmail());
    }

    public String generateToken(Company userDetails) {
        claims.put("role", userDetails.getUserRole().toString());
        return createToken(claims, userDetails.getEmail());
    }
    private String createToken(Map<String, Object> claims, String subject) {

        return "the token is created : "+Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, Admin userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public Boolean validateTokenCompany(String token, Company companyDetails) {
        final String email = extractEmail(token);
        return (email.equals(companyDetails.getEmail()) && !isTokenExpired(token));
    }

}
