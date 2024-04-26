package com.librarymanagement.enums;


public enum OurUserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    OurUserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRole() {
        return roleName;
    }
}