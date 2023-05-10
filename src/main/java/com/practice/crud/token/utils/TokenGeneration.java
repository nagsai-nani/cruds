package com.practice.crud.token.utils;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Component
public class TokenGeneration {
	String uniqueId=UUID.randomUUID().toString();
	String Jwt_Issuer="auth0";
public String  generateToken(String userName,String password) {
	Date createdAt =new Date();
	Date expiredAt = new Date(createdAt.getTime()+4000);
	String email=Base64.getEncoder().encodeToString(userName.getBytes());
	String userPassword=Base64.getEncoder().encodeToString(password.getBytes());
	
	Algorithm algorithm = Algorithm.HMAC256(uniqueId);
	String token=JWT.create().withIssuer(Jwt_Issuer).withIssuedAt(createdAt).
			withExpiresAt(expiredAt).withClaim("",email).withClaim("", userPassword).sign(algorithm);
	
	return token;
	
}

}
