package com.polar_moviechart.movieservice.domain.service.event.dto;

import lombok.Builder;

public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer movieCode;
    private Integer likeCnt;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer movieCode, Integer likeCnt) {
        this.userId = userId;
        this.movieCode = movieCode;
        this.likeCnt = likeCnt;
    }
    @Override
    public MessageType getType() {
        return MessageType.LIKE;
    }

    @Override
    public Integer getCode() {
        return movieCode;
    }

    @Override
    public Integer getValue() {
        return likeCnt;
    }
}