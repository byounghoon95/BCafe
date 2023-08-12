package com.example.bcafe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeEnum {

    SUCCESS("0000","SUCCESS"),
    PRODUCT_NOTFOUND("2000","VALID PRODUCT IS NOT EXIST"),

    UNKNOWN_ERROR("9999","UNKNOWN_ERROR"),

    ;

    private final String code;
    private final String message;
}
