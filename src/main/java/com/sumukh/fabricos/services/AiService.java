package com.sumukh.fabricos.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
@AllArgsConstructor
public class AiService {
    private final RestTemplate restTemplate;

    private final String djangoApiUrl = "http://localhost:8000/api/enhance_pro";

    public String correctText(String text) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            Map<String, String> requestBody = Map.of("user_text", text);
            String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
            headers.setContentLength(jsonBody.getBytes().length);
            
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
            Map response = restTemplate.postForObject(djangoApiUrl, entity, Map.class);

            if (response != null && response.containsKey("improved_text")) {
                return response.get("improved_text").toString();
            }
            return "No Response";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Caught an Exception";
        }
    }
}
