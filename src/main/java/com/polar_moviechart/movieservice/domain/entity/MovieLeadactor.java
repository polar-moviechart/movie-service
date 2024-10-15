package com.polar_moviechart.movieservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "movie_leadactor")
@RequiredArgsConstructor
public class MovieLeadactor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "movie_code", referencedColumnName = "code", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "leadactor_code", referencedColumnName = "code", nullable = false)
    private Leadactor leadactor;
}
