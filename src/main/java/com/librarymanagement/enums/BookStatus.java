package com.librarymanagement.enums;

public enum BookStatus {
    BORROWED("BORROWED"),
    SOLD("SOLD"),
    IN_LIBRARY("IN_LIBRARY"),
    DELETED("DELETED");

    private final String value;

    BookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
