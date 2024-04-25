package com.librarymanagement.enums;

public enum Category {
    ROMAN("ROMAN"),
    HIKAYE("HIKAYE"),
    BILIM_KURGU("BILIM_KURGU"),
    DRAM("DRAM"),
    POLISIYE("POLISIYE"),
    KORKU("KORKU"),
    ASK("ASK"),
    GERILIM("GERILIM"),
    BIOGRAFI("BIOGRAFI"),
    TARIH("TARIH"),
    SEYAHAT("SEYAHAT"),
    YEMEK_KITABI("YEMEK_KITABI"),
    SANAT("SANAT"),
    DIN("DIN"),
    FELSEFE("FELSEFE"),
    KENDINE_YARDIM("KENDINE_YARDIM"),
    IS("IS"),
    DIGER("DIGER");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}