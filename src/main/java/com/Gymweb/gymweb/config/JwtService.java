package com.Gymweb.gymweb.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="F7C28E82EE4CB5FA5BA452B213D46134ART123ASD12A";

    public String extractUsername(String token) {
        return extractCliam(token, Claims::getSubject);
    }

    //Generate token using only UserDetails
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }


    //Generate token from extraCliams and userDetails)
    public String generateToken(
            Map<String, Object>extraCliams,
            UserDetails userDetails)
    {
        return Jwts
                .builder()
                .setClaims(extraCliams)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Method to validate token
    public boolean isTokenValid(String token, UserDetails userDetails){ // is the token belongs to the user details ?
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //checking if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //This method is called to get the expiration date of the token.The expiration date of the token is compared to the current date (new Date).
    private Date extractExpiration(String token) {
        return extractCliam(token, Claims::getExpiration);
    }


    // Extract a single claim
    public <T> T extractCliam(String token, Function<Claims,T> claimsResolver){ //Generic method that can return any type of value
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //Extracting all the caliams
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte []  keyByte  = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
