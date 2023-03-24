package com.votation.api.helpers;

public class MaskSensitiveData {
    public static String mask(String value) {
        final String CPF_REGEX = "(?<=.{3}).";
        return value.replaceAll(CPF_REGEX, "*");
    }
}