package com.polar_moviechart.movieservice.event.dto;

public interface MessageDto {
    MessageType getType();
    Integer getCode();
    Object getValue();

    Long getUserId();
}