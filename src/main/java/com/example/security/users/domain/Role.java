package com.example.security.users.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum Role {

    ADMIN, USER, MANAGER;

    //post할떄 json에서 문자열로 들어오니까 여기서 emum타입으로 변환함
    @JsonCreator
    public static Role fromString(String str) {
        for (Role value : Role.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        //default
        return USER;
    }

    //get에서 enum타입을 Json으로 응답할때 소문자로
    @Override
    @JsonValue
    public String toString() {
        return this.name().toLowerCase();
    }
}
