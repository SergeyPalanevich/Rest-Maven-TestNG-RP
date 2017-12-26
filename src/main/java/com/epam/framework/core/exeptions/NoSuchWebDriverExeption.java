package com.epam.framework.core.exeptions;

public class NoSuchWebDriverExeption extends RuntimeException{
    public NoSuchWebDriverExeption(String message) {
        super(message);
    }
}
