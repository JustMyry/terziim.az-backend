package com.terziim.backend.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.terziim.backend.security.constans.SecurityConstants;
import com.terziim.backend.security.model.AppUserPrincipal;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JwtProvider {


    @Value("${jwt.secretpass}")
    private String secret;

    public String generateJWT(AppUserPrincipal appUserPrincipal) {
        String[] claims = getClaimsFromUser(appUserPrincipal);
        String token = SecurityConstants.TOKEN_PREFIX + JWT.create().withIssuer(SecurityConstants.GET_ARRAYS_LLC).withAudience(SecurityConstants.GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(appUserPrincipal.getUserId())
                .withArrayClaim(SecurityConstants.AUTHORITIES, claims).withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        System.out.println("JwtProvider, withSubject: " + appUserPrincipal.getUserId());

        return token;
    }



    private String[] getClaimsFromUser(AppUserPrincipal appUserPrincipal) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : appUserPrincipal.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }



    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }



    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(SecurityConstants.AUTHORITIES).asArray(String.class);
    }



    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstants.GET_ARRAYS_LLC).build();
        }catch (JWTVerificationException ex) {
            throw new JWTVerificationException(SecurityConstants.TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }



    public Authentication getAuthentication (String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) request));
        return authenticationToken;
    }



    public boolean isTokenValid (String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }



    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }



    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    public boolean isJwtBelongToUser(String userId, String jwt){
        return getSubject(jwt).equals(userId);
    }



}

