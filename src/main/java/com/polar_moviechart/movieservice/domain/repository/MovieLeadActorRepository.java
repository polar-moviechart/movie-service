package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.entity.MovieLeadactor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieLeadActorRepository extends JpaRepository<MovieLeadactor, Long> {
}
