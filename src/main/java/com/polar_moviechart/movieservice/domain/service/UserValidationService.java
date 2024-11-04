package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final RestTemplate restTemplate;
    private final String userServiceUrl = "http://localhost:8082/api/v1/users/";

    public boolean isUserExists(Long userId) {
        String requestUrl = userServiceUrl + userId;
        return restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody()
                .getIsSuccess();
    }
}
