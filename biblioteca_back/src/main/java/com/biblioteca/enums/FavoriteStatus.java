package com.biblioteca.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FavoriteStatus {

    NOT_FAVORITE(0),
    FAVORITE(1);

    private final Integer value;

    public Integer getValue() {
        return value;
    }

    public static FavoriteStatus fromInt(int value) {
        for (FavoriteStatus status : FavoriteStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value:" + value);
    }
}
