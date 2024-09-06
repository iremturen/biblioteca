package com.biblioteca.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BookStatus {

    NOW_READING("NOW_READING"),
    WILL_READ("WILL_READ"),
    FINISHED("FINISHED");

    private final String value;

    public String getValue() {
        return value;
    }
}
