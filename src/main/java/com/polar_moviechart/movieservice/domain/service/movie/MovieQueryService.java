package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.repository.MovieRepository;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovieQueryService {
    private final MovieRepository movieRepository;
    private final MovieDailyStatsQueryService movieDailyStatsQueryService;
    private final MovieRatingQueryService movieRatingQueryService;

    public Page<MovieDto> getMovies(LocalDate targetDateReq, PageRequest pageRequest) {
        LocalDate targetDate = Optional.ofNullable(targetDateReq)
                .orElseGet(movieDailyStatsQueryService::findLatestDate);

        return movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageRequest);
    }

    public MovieDailyStatsResponse getMovieStats(int code, int limit, StatType statType) {
        PageRequest pageable = PageRequest.of(0, limit);
        return movieDailyStatsQueryService.getMovieDailyStats(code, pageable, statType);
    }

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingQueryService.getUserMovieRating(code, userId);
    }

    public List<LocalDate> getStatDates() {
        return movieDailyStatsQueryService.getDates();
    }

    public void validateExists(int code) {
        if (!isExists(code)) {
            throw new MovieBusinessException(ErrorCode.MOVIE_DOESNT_EXISTS);
        }
    }

    public MovieDetailsDto getMovie(int code) {
        return MovieDetailsDto.from(fetchMovie(code));
    }

    public boolean isExists(int code) {
        return movieRepository.existsByCode(code);
    }

    public Movie fetchMovie(int code) {
        return movieRepository.findByCode(code)
                .orElseThrow(() -> new MovieBusinessException(ErrorCode.MOVIE_DOESNT_EXISTS));
    }

    public List<MovieDto> getMoviesByCodes(List<Integer> codes) {
        List<Movie> movies = movieRepository.findByCodeIn(codes);
        List<MovieDto> movieDtos = MovieDto.listFrom(movies);

        movieDtos.stream().forEach(movieDto -> movieDto.setIsLike(true));

        return movieDtos;
    }
}
