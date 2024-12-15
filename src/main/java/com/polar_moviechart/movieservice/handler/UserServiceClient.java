package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.handler.dtos.MovieLikesRes;
import com.polar_moviechart.movieservice.handler.dtos.UserMoviesLikeReq;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

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

    public <T> T sendGetRequest(String endPoint, Map<String, Object> params, Class<T> responseType) {
        String requestUrl = userServiceUrl + endPoint;
        String urlWithParams = getUrlWithParams(params, requestUrl);

        CustomResponse<T> response = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomResponse<T>>() {}
        ).getBody();

        if (!response.getIsSuccess()) {
            throw new IllegalArgumentException(response.getErrorMsg());
        }

        return response.getData();
    }

    public Object postRequest(Long userId, Object requestBody) {
        String requestUrl = userServiceUrl + userId;

        return restTemplate.postForEntity(requestUrl, requestBody, CustomResponse.class)
                .getBody().getData();
    }

    private String getUrlWithParams(Map<String, Object> params, String requestUrl) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl);
        if (params != null && !params.isEmpty()) {
            params.forEach(uriBuilder::queryParam);
        }
        return uriBuilder.toUriString();
    }

    public List<MovieLikesRes> sendPostRequest(String endPoint, UserMoviesLikeReq userMoviesLikeReq, ParameterizedTypeReference<List<MovieLikesRes>> responseType) {
        String requestUrl = userServiceUrl + endPoint;
        return restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(userMoviesLikeReq),
                responseType
        ).getBody();
    }
}
