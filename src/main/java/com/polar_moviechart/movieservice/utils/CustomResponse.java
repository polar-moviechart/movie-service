package com.polar_moviechart.movieservice.utils;

import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.ErrorInfo;
import lombok.Getter;

import java.util.Optional;

@Getter
public class CustomResponse<T> {
    private Boolean isSuccess = true;
    private String errorMsg = null;
    private String code = null;
    private Optional<T> data;

    public CustomResponse(T data) {
        this.data = Optional.ofNullable(data);
    }

    public CustomResponse(ErrorCode errorCode) {
        this.errorMsg = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.isSuccess = false;
    }

    public CustomResponse(ErrorInfo errorInfo) {
        this.errorMsg = errorInfo.getMessage();
        this.code = errorInfo.getCode();
        this.isSuccess = false;
    }

    public void setCode(ErrorCode errorCode) {
        this.errorMsg = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.isSuccess = false;
    }

    public void setInfo(ErrorInfo errorInfo) {
        this.errorMsg = errorInfo.getMessage();
        this.code = errorInfo.getCode();
        this.isSuccess = false;
    }

    public void setCode(String errorCode) {
        this.code = errorCode;
    }
}
