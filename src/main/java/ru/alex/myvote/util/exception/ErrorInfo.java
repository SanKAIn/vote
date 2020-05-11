package ru.alex.myvote.util.exception;

public class ErrorInfo {
    private final String url;
    private final String[] details;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this(url, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    public ErrorInfo(CharSequence requestUrl, String... details) {
        this.url = requestUrl.toString();
        this.details = details;
    }
}