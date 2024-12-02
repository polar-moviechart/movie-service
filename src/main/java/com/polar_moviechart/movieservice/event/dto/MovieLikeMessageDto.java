package com.polar_moviechart.movieservice.event.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer code;
    private Integer value;
    private MessageType type;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer code, Integer value, MessageType type) {
        this.userId = userId;
        this.code = code;
        this.value = value;
        this.type = type;
    }
    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}