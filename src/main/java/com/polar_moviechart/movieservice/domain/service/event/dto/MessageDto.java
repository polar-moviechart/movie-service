package com.polar_moviechart.movieservice.domain.service.event.dto;

public interface MessageDto {
    MessageType getType();
    Integer getCode();
    Object getValue();
}