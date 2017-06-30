package com.isssr.foodemperors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isssr.foodemperors.dto.CommissionDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by marco on 30/06/17.
 */
@Service
public class RequestService {

    public void setMapper(){
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void sendCommissionDTO(CommissionDTO commissionDTO,String host){
        boolean send = false;
        this.setMapper();
        while (!send){
            try {
                HttpResponse<JsonNode> res = Unirest.post(host).header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(commissionDTO).asJson();
                send = true;
            } catch (UnirestException e) {
                System.out.println("Cannot post");
            }
        }
    }


}
