package com.polar_moviechart.movieservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.movieservice.handler.dtos.MovieLikesRes;
import com.polar_moviechart.movieservice.handler.dtos.RestResponsePage;
import com.polar_moviechart.movieservice.handler.dtos.UserMoviesActivityReq;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private final ObjectMapper objectMapper;

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

        return objectMapper.convertValue(response.getData(), responseType);
    }

    private String getUrlWithParams(Map<String, Object> params, String requestUrl) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl);
        if (params != null && !params.isEmpty()) {
            params.forEach(uriBuilder::queryParam);
        }
        return uriBuilder.toUriString();
    }

    public List<MovieLikesRes> sendPostRequest(String endPoint, UserMoviesActivityReq userMoviesActivityReq, ParameterizedTypeReference<List<MovieLikesRes>> responseType) {
        String requestUrl = userServiceUrl + endPoint;
        return restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(userMoviesActivityReq),
                responseType
        ).getBody();
    }

    public <T> Page<T> sendPagedPostRequest(String endpoint,
                                                    UserMoviesActivityReq userMoviesActivityReq,
                                                    PageRequest pageable,
                                                    ParameterizedTypeReference<RestResponsePage<T>> responseType) {
        String requestUrl = userServiceUrl + endpoint;
        ResponseEntity<RestResponsePage<T>> responseEntity = restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(userMoviesActivityReq),
                responseType
        );

        RestResponsePage<T> restResponsePage = responseEntity.getBody();
        if (restResponsePage == null) {
            return Page.empty(); // 응답이 없을 경우 빈 페이지 반환
        }
        return restResponsePage;
    }
}
