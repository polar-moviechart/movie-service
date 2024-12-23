package com.polar_moviechart.movieservice.handler;

import com.polar_moviechart.movieservice.domain.dto.UserActivityInfo;
import com.polar_moviechart.movieservice.domain.enums.Category;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.movieservice.event.dto.MovieRatingMessageDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.handler.dtos.MovieLikesRes;
import com.polar_moviechart.movieservice.handler.dtos.MovieRatingRes;
import com.polar_moviechart.movieservice.handler.dtos.RestResponsePage;
import com.polar_moviechart.movieservice.handler.dtos.UserMoviesActivityReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<MovieLikesRes> getUserMovieLikes(List<Integer> movieCodes, Long userId, PageRequest pageable) {
        String endPoint = String.format("/movies/likes?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
        UserMoviesActivityReq userMoviesActivityReq = new UserMoviesActivityReq(userId, movieCodes);
        return userServiceClient.sendPagedPostRequest(
                endPoint, userMoviesActivityReq, pageable, new ParameterizedTypeReference<RestResponsePage<MovieLikesRes>>() {});
    }

    public Page<MovieRatingRes> getUserMovieRatings(Long userId, PageRequest pageable) {
        String endPoint = String.format("/movies/ratings?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
        UserMoviesActivityReq userMoviesActivityReq = new UserMoviesActivityReq(userId);
        return userServiceClient.sendPagedPostRequest(
                endPoint, userMoviesActivityReq, pageable, new ParameterizedTypeReference<RestResponsePage<MovieRatingRes>>() {});
    }

    public List<Integer> getUserMovieLikes(Long userId, PageRequest pageable) {
        String endPoint = "/movies/likes";
        UserMoviesActivityReq userMoviesActivityReq = new UserMoviesActivityReq(userId);
        List<MovieLikesRes> movieLikesRes = userServiceClient.sendPostRequest(
                endPoint, userMoviesActivityReq, new ParameterizedTypeReference<List<MovieLikesRes>>() {});
        return movieLikesRes.stream().map(MovieLikesRes::getMovieCode).toList();
    }

    public UserActivityInfo getUserActivity(Long userId, int code, Category category) {
        String categoryString = category.toString();
        String endPoint = String.format(
                "/%s/%s/activities/%s", categoryString, userId, code);
        return userServiceClient.sendGetRequest(endPoint, null, UserActivityInfo.class);
    }
}
