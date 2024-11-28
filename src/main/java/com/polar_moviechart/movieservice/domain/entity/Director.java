package com.polar_moviechart.movieservice.domain.entity;

import com.polar_moviechart.movieservice.domain.service.dtos.MovieDirectorDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "directors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "director")
    @Column(nullable = false)
    private List<MovieDirector> movies = new ArrayList<>();

    @Builder
    public Director(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public MovieDirectorDto toDto() {
        return new MovieDirectorDto(code, name);
    }
}
