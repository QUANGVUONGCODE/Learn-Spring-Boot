package com.API.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    DELETE_USER(1006, "DELETE USER FALIED"),
    UNICATEGORIZE_EXCEPTION(200, "SUCCESSFULLY"),
    INVALID_KEY(1001, "Invalid key"),
    INVALID_ID(1005, "INVALID ID"),
    USER_EXISTS(1002, "User already exists"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    USER_NOT_FOUND(1007, "User not found"),
    UNAUTHENCATED(1008, "UNAUTHENCATED");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    int code;
    String message;
}
