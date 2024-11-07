package com.polar_moviechart.movieservice.exception;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    DEFAULT_ERROR("9999", "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다.");

    private String code = "M101";
    private final String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorInfo(String message) {
        this.message = message;
    }
}
