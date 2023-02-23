package com.drinkshop.services.impl;

import com.drinkshop.services.IGHNService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class GHNService implements IGHNService {

    @Value("${ghn.token}")
    private String token;

    private final WebClient webClient;

    public GHNService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://online-gateway.ghn.vn/shiip/public-api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Override
    public Map<String, Object> getProvince() {
        return webClient.get().uri("/master-data/province")
                .header("token", token)
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> getDistrict(int provinceId) {
        Map<String, Integer> bodyMap = new HashMap<>();
        bodyMap.put("province_id", provinceId);
        return webClient.post().uri("/master-data/district")
                .header("token", token)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public Map<String, Object> getWard(int districtId) {
        Map<String, Integer> bodyMap = new HashMap<>();
        bodyMap.put("district_id", districtId);
        return webClient.post().uri("/master-data/ward")
                .header("token", token)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .block()
                .bodyToMono(Map.class)
                .block();
    }
}
