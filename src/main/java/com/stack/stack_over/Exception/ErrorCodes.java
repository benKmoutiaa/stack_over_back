package com.stack.stack_over.Exception;

public enum ErrorCodes {
    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),
    USER_ALREADY_EXISTS(1002),
    UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID(1003),
    ROLE_NOT_FOUND(2000);
    private int code;
    ErrorCodes (int code)
    {
        this.code=code;
    }
    public int getCode()
    {
        return code;
    }
}
