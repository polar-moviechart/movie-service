package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.movieservice.event.dto.MovieRatingMessageDto;
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

    public void validateUserMovieLikeState(MovieLikeMessageDto message) {
        String endPoint = String.format(
                "%s/movies/%s/likes",message.getUserId(), message.getCode());
        Boolean isUserMovieLikeState = userServiceClient.sendGetRequest(endPoint, null, Boolean.class);

        if (!isUserMovieLikeState.equals(message.getValue())) {
            throw new MovieBusinessException(ErrorCode.DEFAULT_ERROR);
        }
    }

    public void validateUserRatingState(MovieRatingMessageDto message) {
        String endPoint = String.format(
                "%s/movies/%s/ratings",message.getUserId(), message.getCode());
        Double userRating = userServiceClient.sendGetRequest(endPoint, null, Double.class);

        if (!userRating.equals(message.getValue())) {
            throw new MovieBusinessException(ErrorCode.DEFAULT_ERROR);
        }
    }
}
