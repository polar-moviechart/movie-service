package com.polar_moviechart.movieservice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "movie_ratings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(nullable = false)
    private Double rating;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public MovieRating(Long userId, Integer movieCode, Double rating) {
        this.userId = userId;
        this.code = movieCode;
        this.rating = rating;
    }

    public MovieRating(Long userId, Integer movieCode, Double rating, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.code = movieCode;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setRating(double ratingValue) {
        this.rating = ratingValue;
    }
}
