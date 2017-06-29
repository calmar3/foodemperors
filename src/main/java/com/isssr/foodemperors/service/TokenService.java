package com.isssr.foodemperors.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isssr.foodemperors.config.AppConfig;
import com.isssr.foodemperors.repository.UserRepository;
import com.isssr.foodemperors.utils.TokenPayload;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by marco on 29/06/17.
 */
@Service
public class TokenService {

    @Inject
    private UserRepository userRepository;

    public TokenPayload validateUser(String token){
        if (token == null)
            return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            TokenPayload decoded = mapper.readValue(Jwts.parser().setSigningKey(AppConfig.key)
                    .parseClaimsJws(token).getBody().getSubject(),TokenPayload.class);
            if (userRepository.findByUsernameAndRole(decoded.getUsername(),decoded.getRole()) == null)
                return null;
            else
                return decoded;
        } catch (SignatureException e) {
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateToken(String username,String password){
        ObjectMapper mapper = new ObjectMapper();
        TokenPayload tokenPayload = new TokenPayload(username,password);
        try {
            String payloadString = mapper.writeValueAsString(tokenPayload);
            String compactJws = Jwts.builder()
                    .setSubject(payloadString)
                    .signWith(SignatureAlgorithm.HS512, AppConfig.key)
                    .compact();
            return compactJws;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
