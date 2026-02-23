package ru.sicampus.bootcamp2026.exceptions;

public class UserNotFoundError extends RuntimeException {
    public UserNotFoundError(String message) {
        super(message);
    }
}
