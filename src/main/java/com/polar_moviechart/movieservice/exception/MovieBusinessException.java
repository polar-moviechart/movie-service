package com.polar_moviechart.movieservice.exception;

public class MovieBusinessException extends RuntimeException {
    private static final String msg = "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다.";
    private ErrorInfo errorInfo;

    public MovieBusinessException() {
        super(msg);
    }

    public MovieBusinessException(ErrorInfo errorInfo, Throwable cause) {
        super(errorInfo.getMessage(), cause);
        this.errorInfo = errorInfo;
    }

    public MovieBusinessException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }

    public MovieBusinessException(Throwable cause) {
        super(msg, cause);
    }
}
