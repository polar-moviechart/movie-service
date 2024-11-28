package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.entity.MovieLeadactor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MovieLeadactorDto {
    private final Integer code;
    private final String name;

    public static List<MovieLeadactorDto> listFrom(List<MovieLeadactor> leadactors) {
        return leadactors.stream()
                .map(MovieLeadactorDto::from)
                .toList();
    }

    public static MovieLeadactorDto from(MovieLeadactor leadactors) {
        return new MovieLeadactorDto(
                leadactors.getLeadactor().getCode(),
                leadactors.getLeadactor().getName()
        );
    }
}
