package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.proxy.UserServiceProxy;
import com.polar_moviechart.movieservice.domain.service.MovieDailyStatsQueryService;
import com.polar_moviechart.movieservice.domain.service.MovieQueryService;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieDailyStatsQueryService movieDailyStatsQueryService;
    private final MovieQueryService movieQueryService;
    private final UserServiceProxy userServiceProxy;

    public List<MovieDto> getMovies(LocalDate targetDateReq, int page, int size) {
        LocalDate targetDate = Optional.ofNullable(targetDateReq)
                .orElseGet(movieDailyStatsQueryService::findLatestDate);
        PageRequest pageable = PageRequest.of(page, size);

        return movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);
    }

    public MovieDetailsDto getMovie(int code) {
        return movieQueryService.getMovie(code);
    }


    public MovieDailyStatsResponse getMovieStats(int code, int limit, StatType statType) {
        PageRequest pageable = PageRequest.of(0, limit);
        return movieDailyStatsQueryService.getMovieDailyStats(code, pageable, statType);
    }

    public List<LocalDate> getStatDates() {
        return movieDailyStatsQueryService.getDates();
    }

    public Integer getLikes(int code) {
        userServiceProxy.getMovieLikes(code);
        return null;
    }
}
