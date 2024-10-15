package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.entity.MovieDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDirectorRepository extends JpaRepository<MovieDirector, Long> {
}
