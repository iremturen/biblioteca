package com.biblioteca.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum BookStatus {

    FAVORITES("FAVORITES"),
    NOW_READING("NOW_READING"),
    WILL_READ("WILL_READ"),
    FINISHED("FINISHED");

    private final String value;

    public String getValue() {
        return value;
    }
}
