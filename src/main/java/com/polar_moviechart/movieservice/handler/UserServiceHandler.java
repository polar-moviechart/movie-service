package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.domain.dto.UserActivityInfo;
import com.polar_moviechart.movieservice.domain.enums.Category;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.movieservice.event.dto.MovieRatingMessageDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.handler.dtos.MovieLikesRes;
import com.polar_moviechart.movieservice.handler.dtos.UserMoviesLikeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

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

    public Double getUserMovieRating(Long userId, int code) {
        String endPoint = String.format(
                "%s/movies/%s/ratings",userId, code);
        return userServiceClient.sendGetRequest(endPoint, null, Double.class);
    }

    public List<MovieLikesRes> getUserMovieLikes(List<Integer> movieCodes, Long userId, PageRequest pageable) {
        String endPoint = "/movies/likes";
        UserMoviesLikeReq userMoviesLikeReq = new UserMoviesLikeReq(userId, movieCodes);
        List<MovieLikesRes> movieLikesRes = userServiceClient.sendPostRequest(
                endPoint, userMoviesLikeReq, new ParameterizedTypeReference<List<MovieLikesRes>>() {});
        return movieLikesRes;
    }

    public List<Integer> getUserMovieLikes(Long userId, PageRequest pageable) {
        String endPoint = "/movies/likes";
        UserMoviesLikeReq userMoviesLikeReq = new UserMoviesLikeReq(userId);
        List<MovieLikesRes> movieLikesRes = userServiceClient.sendPostRequest(
                endPoint, userMoviesLikeReq, new ParameterizedTypeReference<List<MovieLikesRes>>() {});
        return movieLikesRes.stream().map(MovieLikesRes::getMovieCode).toList();
    }

    public UserActivityInfo getUserActivity(Long userId, int code, Category category) {
        String categoryString = category.toString();
        String endPoint = String.format(
                "/%s/%s/activities/%s", categoryString, userId, code);
        return userServiceClient.sendGetRequest(endPoint, null, UserActivityInfo.class);
    }
}
