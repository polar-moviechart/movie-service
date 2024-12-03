package com.polar_moviechart.movieservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DEFAULT_ERROR("9999", "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다."),
    USER_NOT_EXISTS("U201", "유저가 존재하지 않습니다."),
    MOVIE_DOESNT_EXISTS("M101", "영화가 존재하지 않습니다."),
    RATING_NOT_EXISTS("M102", "영화 평점이 존재하지 않습니다."),
    MESSAGE_PARSE_ERROR("M103", "메시지 파싱에 실패했습니다."),
    MESSAGE_PROCESS_EXCEPTION("M104", "메시지 처리 중 오류가 발생했습니다.");

    private String code = "M101";
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorCode(String message) {
        this.message = message;
    }
}

