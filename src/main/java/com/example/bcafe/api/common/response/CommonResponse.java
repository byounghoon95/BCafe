package com.example.bcafe.api.common.response;

import com.example.bcafe.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private String code;
    private String message;
    private T info;

    public CommonResponse(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public CommonResponse(T info) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.message = CodeEnum.SUCCESS.getMessage();
        this.info = info;
    }

    public CommonResponse(CodeEnum codeEnum, T info) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.info = info;
    }
}
