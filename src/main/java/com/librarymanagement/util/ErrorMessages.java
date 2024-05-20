package com.librarymanagement.util;

public enum ErrorMessages {
    MEMBER_NOT_FOUND("MEMBER NOT FOUND"),
    USER_NOT_FOUND("USER NOT FOUND"),
    BOOK_NOT_FOUND("BOOK NOT FOUND"),
    UNAUTHORIZED_MEMBER("UNAUTHORIZED MEMBER"),
    PUBLISHER_NOT_FOUND("PUBLISHER_NOT_FOUND"),
    AUTHOR_NOT_FOUND("AUTHOR NOT FOUND");
    private final String value;
    ErrorMessages(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}