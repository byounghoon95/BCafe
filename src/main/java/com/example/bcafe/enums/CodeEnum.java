package com.example.bcafe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeEnum {

    SUCCESS("0000","SUCCESS"),
    MEMBER_NOTFOUND("1000","VALID MEMBER IS NOT EXIST"),
    PRODUCT_NOTFOUND("2000","VALID PRODUCT IS NOT EXIST"),
    LESS_STOCK("3000","LESS STOCK QUANTITY THAN REQUIRED STOCK"),

    UNKNOWN_ERROR("9999","UNKNOWN_ERROR"),

    ;

    private final String code;
    private final String message;
}
