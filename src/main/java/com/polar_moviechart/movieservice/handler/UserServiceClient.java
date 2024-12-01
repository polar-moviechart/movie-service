package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient {
    private final RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceUrl;

    public Object getRequest(Long userId) {
        String requestUrl = userServiceUrl + userId;

        return restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody().getData();
    }

    public Object postRequest(Long userId, Object requestBody) {
        String requestUrl = userServiceUrl + userId;

        return restTemplate.postForEntity(requestUrl, requestBody, CustomResponse.class)
                .getBody().getData();
    }
}
