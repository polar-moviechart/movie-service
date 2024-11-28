package com.polar_moviechart.movieservice.exception;

import lombok.Getter;

@Getter
public class ErrorInfo {

    private static String defaultCode = "M101";
    private String code;
    private final String message;

    public ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        if (this.code == null) {
            return defaultCode;
        }
        return this.code;
    }
}
