package com.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    DELETE_USER(1006, "DELETE USER FALIED", HttpStatus.BAD_REQUEST),
    UNICATEGORIZE_EXCEPTION(200, "SUCCESSFULLY", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),
    INVALID_ID(1005, "INVALID ID", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1002, "User already exists", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1007, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENCATED(1008, "UNAUTHENCATED error", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1010, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
