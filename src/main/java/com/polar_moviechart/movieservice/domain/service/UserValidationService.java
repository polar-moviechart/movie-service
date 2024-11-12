package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.exception.ErrorInfo;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final RestTemplate restTemplate;
    @Value("${user-service.url}")
    private String userServiceUrl;

    public void validateUserExists(Long userId) {
        String requestUrl = userServiceUrl + userId;
        Boolean isUserExists = restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody()
                .getIsSuccess();
        if (!isUserExists) {
            throw new MovieBusinessException(ErrorInfo.USER_NOT_EXISTS);
        }
    }
}
