package ru.alex.myvote.util.exception;

public class NotFoundException extends ApplicationException {

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String arg) {
        super(arg);
    }
}