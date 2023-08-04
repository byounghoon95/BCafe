package com.example.bcafe.exception;

import com.example.bcafe.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private String code;
    private String message;

    public CustomException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }
}
