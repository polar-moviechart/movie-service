package com.polar_moviechart.movieservice.exception;

public class MovieBusinessException extends RuntimeException {
    private static final String msg = "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다.";

    public MovieBusinessException() {
        super(msg);
    }

    public MovieBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieBusinessException(String message) {
        super(message);
    }

    public MovieBusinessException(Throwable cause) {
        super(msg, cause);
    }
}
