package com.polar_moviechart.movieservice.repository;

import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {
    Optional<MovieRating> findByCodeAndUserId(Integer code, Long userId);
}
