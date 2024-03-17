package com.librarymanagement.util;

public enum ErrorMessages {
    MEMBER_NOT_FOUND("MEMBER NOT FOUND"),
    UNAUTHORIZED_MEMBER("UNAUTHORIZED MEMBER");
    private final String value;
    ErrorMessages(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}