package com.example.bcafe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeEnum {

    SUCCESS("0000","SUCCESS"),

    ;

    private final String code;
    private final String message;
}
