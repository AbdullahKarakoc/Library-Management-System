package com.librarymanagement.enums;


public enum MemberRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    MemberRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRole() {
        return roleName;
    }
}