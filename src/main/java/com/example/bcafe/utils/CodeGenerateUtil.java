package com.example.bcafe.utils;

public class CodeGenerateUtil {
    public static String createNextCode(String latestCode, String code) {
        if (latestCode == null) {
            return code + "00001";
        }

        int latestCodeInt = Integer.parseInt(latestCode.substring(1));
        int nextCodeInt = latestCodeInt + 1;

        return code + String.format("%05d", nextCodeInt);
    }
}
