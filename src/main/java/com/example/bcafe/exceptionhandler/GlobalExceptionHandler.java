package com.example.bcafe.exceptionhandler;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.enums.CodeEnum;
import com.example.bcafe.exception.CustomException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResponse handlerCustomException(CustomException e) {
        return CommonResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handlerValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return CommonResponse.builder()
                .code(CodeEnum.UNKNOWN_ERROR.getCode())
                .message(errorMessage)
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResponse handlerException(Exception e) {
        return CommonResponse.builder()
                .code(CodeEnum.UNKNOWN_ERROR.getCode())
                .message(e.getMessage())
                .build();
    }
}
