package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler {
    private final UserServiceClient userServiceClient;

    public void validateUserExists(Long userId) {
        boolean isUserExists = (boolean) userServiceClient.getRequest(userId);
        if (!isUserExists) {
            throw new MovieBusinessException(ErrorCode.USER_NOT_EXISTS);
        }
    }
}
