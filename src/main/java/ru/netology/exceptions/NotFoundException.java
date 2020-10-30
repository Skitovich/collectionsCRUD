package ru.netology.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public void getMessage(String message) {
        System.out.println(message);
    }
}
