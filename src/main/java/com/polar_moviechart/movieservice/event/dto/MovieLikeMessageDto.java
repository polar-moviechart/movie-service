package com.polar_moviechart.movieservice.event.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer code;
    private boolean value;
    private MessageType type;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer code, boolean value, MessageType type) {
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
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }
}