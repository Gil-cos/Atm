package com.arm.atm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
    static final String CLAIM_KEY_ID = "id";
    static final String CLAIM_KEY_USERNAME = "name";

	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date date = new Date();
		Date expirationDate = new Date(date.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API ATM")
				.setClaims(getClaims(user))
				.setIssuedAt(date)
				.setSubject(user.getId().toString())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
    public Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_ID, user.getId());

        return claims;
    }

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
