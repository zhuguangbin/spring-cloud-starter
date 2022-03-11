package org.example.spring.starter.backend.exception;

public enum Status
{
    OK(0, "OK", "成功"),
    ALREADY_EXISTS(1, "already exists", "记录已经存在"),
    NOT_EXISTS(2, "not exits", "记录不存在")
    ;

    private final int code;
    private final String en;
    private final String zh;

    Status(int code, String en, String zh)
    {
        this.code = code;
        this.en = en;
        this.zh = zh;
    }
}
