package ru.hogwarts.school.exception;

public class FileIsTooBigException extends RuntimeException {
    private final int sizeLimit;

    public FileIsTooBigException(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }
}
