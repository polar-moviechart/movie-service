package com.polar_moviechart.movieservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    DEFAULT_ERROR("9999", "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다.");

    private String code = "M101";
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorCode(String message) {
        this.code = "1000";
        this.message = message;
    }
}
