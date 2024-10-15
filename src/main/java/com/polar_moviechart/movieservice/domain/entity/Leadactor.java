package com.polar_moviechart.movieservice.domain.entity;

import com.polar_moviechart.movieservice.domain.service.MovieLeadactorDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "leadactors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leadactor {

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

    @OneToMany(mappedBy = "leadactor")
    @Column(nullable = false)
    private List<MovieLeadactor> movies = new ArrayList<>();

    public MovieLeadactorDto toDto() {
        return new MovieLeadactorDto(code, name);
    }
}
