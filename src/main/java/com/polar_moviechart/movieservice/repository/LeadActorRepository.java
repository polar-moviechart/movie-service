package com.polar_moviechart.movieservice.repository;

import com.polar_moviechart.movieservice.domain.entity.Leadactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadActorRepository extends JpaRepository<Leadactor, Long> {
}
