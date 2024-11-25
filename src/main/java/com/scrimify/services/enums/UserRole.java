package com.scrimify.services.enums;

public enum UserRole {
    ROLE_USER("00", "USER"),
    ROLE_ADMIN("10", "ADMIN");

    private String code;
    private String text;

    UserRole(String code, String text){
        this.code = code;
        this.text = text;
    }

    public String getCode(){return code;}
    public String getText(){return text;}

}
