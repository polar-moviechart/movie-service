package com.polar_moviechart.movieservice.repository;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByCode(int code);

    boolean existsByCode(int code);
}
