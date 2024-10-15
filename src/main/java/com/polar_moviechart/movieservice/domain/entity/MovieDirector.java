package com.polar_moviechart.movieservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "movie_director")
@RequiredArgsConstructor
public class MovieDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate modifiedAt;

    @ManyToOne
    @JoinColumn(name = "movie_code", referencedColumnName = "code", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "director_code", referencedColumnName = "code", nullable = false)
    private Director director;
}
