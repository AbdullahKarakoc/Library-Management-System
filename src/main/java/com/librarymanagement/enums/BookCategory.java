package com.librarymanagement.enums;

public enum BookCategory {
    NOVEL("NOVEL"),
    STORY("STORY"),
    SCIENCE("SCIENCE"),
    DRAMA("DRAMA"),
    DETECTIVE("DETECTIVE"),
    HORROR("HORROR"),
    ROMANCE("ROMANCE"),
    THRILLER("THRILLER"),
    BIOGRAPHY("BIOGRAPHY"),
    HISTORY("HISTORY"),
    TRAVEL("TRAVEL"),
    COOKBOOK("COOKBOOK"),
    ART("ART"),
    RELIGION("RELIGION"),
    PHILOSOPHY("PHILOSOPHY"),
    BUSINESS("BUSINESS"),
    OTHER("OTHER");

    private final String value;

    BookCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}