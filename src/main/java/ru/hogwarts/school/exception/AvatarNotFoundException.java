package ru.hogwarts.school.exception;

public class AvatarNotFoundException extends RuntimeException {
    private final long studentId;

    public AvatarNotFoundException(long studentId) {
        this.studentId = studentId;
    }

    public long getStudentId() {
        return studentId;
    }
}
