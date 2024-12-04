package com.scrimify.services.enums;

public enum MatchStatus {

    STATUS_INITIATED("00", "INITIATED"),
    STATUS_ACCEPTED("10", "ACCEPTED"),
    STATUS_FINISHED("20", "FINISHED")
    ;

    private String code;
    private String label;

    MatchStatus(String code, String label){
        this.code = code;
        this.label = label;
    }
}
