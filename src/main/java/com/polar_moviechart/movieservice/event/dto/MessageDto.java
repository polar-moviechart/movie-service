package com.polar_moviechart.movieservice.event.dto;

public interface MessageDto {
    UserActivityType getType();
    Integer getCode();
    Object getValue();

    Long getUserId();
}