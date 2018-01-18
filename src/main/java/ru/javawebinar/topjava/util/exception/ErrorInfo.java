package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private String url;
    private ErrorType type;
    private String detail;

    public ErrorInfo() {}

    public ErrorInfo(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
    }

    public ErrorType getType() {
        return type;
    }
}