package com.biblioteca.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FavoriteStatus {

    NOT_FAVORITE(0),
    FAVORITE(1);

    private final int value;

    public int getValue() {
        return value;
    }
}
