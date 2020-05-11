package ru.alex.myvote.util.exception;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {

    private final String[] args;

    public ApplicationException(String... args) {
        super(String.format("Not found entity with %s", Arrays.toString(args)));
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
