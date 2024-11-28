package com.polar_moviechart.movieservice.domain.proxy;

import org.springframework.beans.factory.annotation.Value;

public class UserServiceProxy {
    @Value("${user-service.url}")
    private String USER_SERVICE_URL;

    public void getMovieLikes(int code) {

    }
}
